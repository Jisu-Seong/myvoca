package com.example.vocaapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vocaapi.dto.VocaResponseDTO;
import com.example.vocaapi.dto.form.VocaFormDTO;
import com.example.vocaapi.entity.Folder;
import com.example.vocaapi.entity.VocaAndClass;
import com.example.vocaapi.entity.Vocabulary;
import com.example.vocaapi.entity.Wordclass;
import com.example.vocaapi.repository.FolderRepository;
import com.example.vocaapi.repository.VocaAndClassRepository;
import com.example.vocaapi.repository.VocaRepository;
import com.example.vocaapi.repository.WordclassRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class VocaService {
        private final FolderRepository folderRepository;
        private final VocaRepository vRepository;
        private final VocaAndClassRepository vcRepository;
        private final WordclassRepository wRepository;

        // 폴더당 보카 리스트 o
        public List<VocaResponseDTO> getVocaList(Long fid) {
                List<Vocabulary> list = vRepository.findVocaPageListByFid(fid);
                return list.stream().map(voca -> VocaResponseDTO.of(voca)).collect(Collectors.toList());
        }

        // 보카 상세 o
        public VocaResponseDTO getOneVoca(Long vid) {
                return VocaResponseDTO.of(vRepository.getReferenceById(vid));
        }

        // 보카 삭제 o,
        // 관계도 삭제
        public void deleteVoca(Long vid) {
                // 한 보카당 여러 관계일 경우 관계 다삭제

                vRepository.deleteById(vid);

        }

        // 보카 편집
        // 클래스도 경우에 따라 추가/삭제, 그에 따라 관계도 추가/삭제
        public void modifyVoca(Long vid, VocaFormDTO vocaFormDTO) {
                Vocabulary vocabulary = vRepository.getReferenceById(vid);
                vocabulary.changeVocaname(vocaFormDTO.getVocaname());
                vocabulary.changeMark(vocaFormDTO.isMarked());
                vocabulary.changeMeanings(vocaFormDTO.getMeaning());
                vocabulary.changeSentence(vocaFormDTO.getSentence());
                vocabulary.changeUpdateAt();
                vRepository.save(vocabulary);

                Set<String> after = vocaFormDTO.getClasses();
                Set<String> before = new HashSet<>();

                List<Long> beforeLong = vcRepository.findRelationByVid(vid).stream().map(x -> x.getWordclass().getWid())
                                .collect(Collectors.toList());

                for (Long elem : beforeLong) {
                        Optional<Wordclass> x = wRepository.findById(elem);
                        if (x != null) {
                                Wordclass y = x.orElseThrow();
                                before.add(y.getClassname());
                        }
                }
                Set<String> temp1 = after.stream().collect(Collectors.toSet());
                Set<String> temp2 = before.stream().collect(Collectors.toSet());

                after.removeAll(temp2);
                before.removeAll(temp1);

                List<String> aList = new ArrayList<>(after); // 클래스 확인 후 추가, 관계 추가
                List<String> bList = new ArrayList<>(before); // 관계 삭제
                List<VocaAndClass> vcList = vcRepository.findRelationByVid(vid);
                if (vcList != null && vcList.size() != 0) {
                        for (int i = 0; i < vcList.size(); i++) {
                                String str = vcList.get(i).getWordclass().getClassname();
                                if (!aList.contains(str)) {
                                        addRelation(new VocaAndClass(vocabulary, registWordclass(str)));
                                }
                                if (bList.contains(str)) {
                                        deleteRelation(vcList.get(i));
                                }
                        }
                }

        }

        // 보카 추가
        public Long addVoca(Long fid, VocaFormDTO vocaFormDTO) {
                Vocabulary vocabulary = Vocabulary.builder()
                                .folder(folderRepository.getReferenceById(fid))
                                .vocaname(vocaFormDTO.getVocaname())
                                .isMarked(vocaFormDTO.isMarked())
                                .meaning(vocaFormDTO.getMeaning())
                                .sentence(vocaFormDTO.getSentence())
                                .build();
                vRepository.save(vocabulary).getVid();

                Set<String> set = vocaFormDTO.getClasses();
                List<String> list = new ArrayList<>(set);
                for (int i = 0; i < list.size(); i++) {
                        Optional<Wordclass> w1 = getWordclass(list.get(i));
                        if (w1 == null) {
                                Wordclass w = registWordclass(list.get(i));
                                addRelation(new VocaAndClass(vocabulary, w));
                        } else {
                                Wordclass w2 = w1.orElseThrow();
                                addRelation(new VocaAndClass(vocabulary, w2));
                        }
                }
                return vocabulary.getVid();

        }

        // 한 클래스에 해당하는 모든 보카 조회
        public List<VocaResponseDTO> getVocaListByClass(String classname) {
                Wordclass wordclass = wRepository.findByClassname(classname).orElseThrow();
                Long wid = wordclass.getWid();
                List<VocaAndClass> vcList = vcRepository.findRelationByWid(wid);
                List<Long> lList = vcList.stream().map(x -> x.getVocabulary().getVid()).collect(Collectors.toList());
                List<Vocabulary> vList = new ArrayList<>();
                for (Long elem : lList) {
                        vList.add(vRepository.getReferenceById(elem));
                }
                Iterator<Vocabulary> iter = vList.iterator();
                while (iter.hasNext()) {
                        if (iter.next() == null) {
                                iter.remove();
                        }
                }
                return vList.stream().map(x -> VocaResponseDTO.of(x)).collect(Collectors.toList());

        }

        // 한 단어에 해당되는 모든 클래스 조회
        public List<String> getWCNameByOneVoca(Long vid) {
                List<VocaAndClass> vcList = vcRepository.findRelationByVid(vid);
                List<Long> lList = vcList.stream().map(x -> x.getWordclass().getWid()).collect(Collectors.toList());
                List<Wordclass> wList = new ArrayList<>();
                for (Long elem : lList) {
                        wList.add(wRepository.getReferenceById(elem));
                }
                Iterator<Wordclass> iter = wList.iterator();
                while (iter.hasNext()) {
                        if (iter.next() == null) {
                                iter.remove();
                        }
                }
                return wList.stream().map(x -> x.getClassname()).collect(Collectors.toList());
        }

        // 관계 추가
        public void addRelation(VocaAndClass vc) {
                vcRepository.save(vc);
        }

        // 관계 삭제
        public void deleteRelation(VocaAndClass vc) {
                vc.changeVoca(null);
                vc.changeWordClass(null);
                vcRepository.save(vc);
        }

        // 클래스 확인
        public Optional<Wordclass> getWordclass(String classname) {
                return wRepository.findByClassname(classname);

        }

        // 클래스 추가
        public Wordclass registWordclass(String classname) {
                Wordclass wordclass = new Wordclass(classname);
                return wRepository.save(wordclass);
        }

        // 클래스 삭제
        public void deleteWordclass(Long wid) {
                wRepository.deleteById(wid);
        }

}
