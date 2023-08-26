package com.doori.hackerthon.service;

import com.doori.hackerthon.dto.ExamDto;
import com.doori.hackerthon.dto.KeywordDto;
import com.doori.hackerthon.entity.AdminGptEntity;
import com.doori.hackerthon.repository.AdminGptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminGptService {
    private final AdminGptRepository adminGptRepository;

    public void saveExam(){
        List<String> questionList = new ArrayList<>();
        List<String> answerList = new ArrayList<>();
        questionList.add("OSI 모델의 몇 번째 계층에서 데이터의 압축 및 암호화와 같은 보안 기능이 수행됩니까?");
        questionList.add("OSI 모델의 어떤 계층에서 MAC (Media Access Control) 주소를 사용하여 프레임을 관리하고 네트워크 내에서 데이터 프레임을 전달하나요?");
        answerList.add("OSI 모델의 보안 기능은 주로 7번째 계층인 애플리케이션 계층에서 수행됩니다.");
        answerList.add("MAC (Media Access Control) 주소를 사용하여 데이터 프레임을 관리하고 네트워크 내에서 전달하는 계층은 OSI 모델의 2번째 계층인 \"데이터 링크 계층\"입니다.");

        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setQuestion(questionList);
        adminGptEntity.setAnswer(questionList);
        adminGptRepository.save(adminGptEntity);
    }
    public List<String> saveKeyword(){
        List<String> keywordList = new ArrayList<>();
        keywordList.add("TCP");
        keywordList.add("UDP");
        keywordList.add("ICMP");
        keywordList.add("Best Effort");
        keywordList.add("IP프로토콜");
        keywordList.add("라우팅");
        keywordList.add("IP주소");
        keywordList.add("IPv6");
        keywordList.add("메시지 분할 전송");
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setKeyword(keywordList);
        System.out.println(adminGptEntity.getKeyword());
        adminGptRepository.save(adminGptEntity);
        return keywordList;
    }
    public List<KeywordDto> getKeyword(){
        AdminGptEntity adminGptEntity = adminGptRepository.findById(1L).get();
        List<String> keyword = adminGptEntity.getKeyword();
        List<KeywordDto> keywordDtoList = new ArrayList<>();
        System.out.println(keyword.size());
        int count = 1;
        for (String s : keyword){
            keywordDtoList.add(new KeywordDto(count, s));
            count++;
        }
        return keywordDtoList;
    }
    public String saveSummary(){
        String summary  = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }
    public String getSummary(){
        String summary  = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }

//    public List<String> getExam(){
//        String summary  = "summary";
//        AdminGptEntity adminGptEntity = new AdminGptEntity();
//        adminGptEntity.setSummary(summary);
//        System.out.println(adminGptEntity.getSummary());
//        adminGptRepository.save(adminGptEntity);
//        return summary;
//    }

}
