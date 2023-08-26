package com.doori.hackerthon.service;

import com.doori.hackerthon.dto.ExamDto;
import com.doori.hackerthon.dto.KeywordDto;
import com.doori.hackerthon.entity.AdminGptEntity;
import com.doori.hackerthon.repository.AdminGptRepository;
import lombok.RequiredArgsConstructor;
import org.python.antlr.ast.keyword;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminGptService {
    private final AdminGptRepository adminGptRepository;

    public void saveExam() {
        List<String> questionList = new ArrayList<>();
        List<String> answerList = new ArrayList<>();
        questionList.add("OSI 모델의 몇 번째 계층에서 데이터의 압축 및 암호화와 같은 보안 기능이 수행됩니까?");
        questionList.add("OSI 모델의 어떤 계층에서 MAC (Media Access Control) 주소를 사용하여 프레임을 관리하고 네트워크 내에서 데이터 프레임을 전달하나요?");
        questionList.add("Best effort란 무엇을 의미하며, 어떤 종류의 통신 서비스를 설명하나요?");
        questionList.add("Best effort 서비스의 주요 특징 중 하나는 무엇이며, 왜 이것이 중요한가요?");
        questionList.add("라우팅이란 무엇이며, 어떻게 작동하는지 간단히 설명하세요.");
        questionList.add("정적 라우팅은 무엇이고 장단점은 무엇인가요?");
        answerList.add("OSI 모델의 보안 기능은 주로 7번째 계층인 애플리케이션 계층에서 수행됩니다.");
        answerList.add("MAC (Media Access Control) 주소를 사용하여 데이터 프레임을 관리하고 네트워크 내에서 전달하는 계층은 OSI 모델의 2번째 계층인 \"데이터 링크 계층\"입니다.");
        answerList.add("Best effort은 통신 네트워크에서 데이터 전달을 최선을 다해 시도하되, 데이터의 분실, 중복, 순서 변경 및 비트 오류와 같은 문제에 대한 보장을 제공하지 않는 비연결형 서비스를 의미합니다.");
        answerList.add("Best effort 서비스의 주요 특징 중 하나는 네트워크 리소스를 효율적으로 활용하여 대역폭을 최대한 확보하려는 노력입니다. 이것은 네트워크에서 다양한 유형의 트래픽이 경쟁하고, 모든 패킷이 최선을 다해 전달되려고 시도함을 의미합니다.");
        answerList.add("라우팅은 네트워크에서 패킷을 출발지에서 목적지로 전달하는 프로세스입니다. 라우터라고 불리는 네트워크 장비는 패킷의 목적지 주소를 확인하고 최적의 경로를 선택하여 패킷을 다음 라우터로 전송합니다. ");
        answerList.add("정적 라우팅은 라우터 관리자가 수동으로 경로를 설정하는 방법입니다. 라우터가 네트워크 변경 사항을 자동으로 감지하지 않으며 설정이 변경되지 않는 한 항상 동일한 경로를 사용합니다. 장점은 예측 가능하고 간단하며 안정적이지만 네트워크가 크게 확장되면 유지 관리가 어려울 수 있습니다.");

        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setQuestion(questionList);
        adminGptEntity.setAnswer(answerList);
        adminGptRepository.save(adminGptEntity);
    }

    public List<String> saveKeyword() {
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

    public List<String> getKeyword() {
        AdminGptEntity adminGptEntity = adminGptRepository.findById(1L).get();
        List<String> keyword = adminGptEntity.getKeyword();
        List<String> keywordList = new ArrayList<>();
        for (String s : keyword) {
            keywordList.add(s);
        }
        return keywordList;
    }

    public List<ExamDto> getExam() {
        AdminGptEntity adminGptEntity = adminGptRepository.findById(2L).get();
        List<String> questionList = adminGptEntity.getQuestion();
        List<String> answerList = adminGptEntity.getAnswer();

        List<ExamDto> examDtos = new ArrayList<>();
        for (int i = 1; i <= questionList.size(); i++) {
            examDtos.add(new ExamDto(i, questionList.get(i - 1), answerList.get(i - 1)));
        }
        return examDtos;
    }

    public String saveSummary() {
        String summary = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }

    public String getSummary() {
        String summary = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }


}
