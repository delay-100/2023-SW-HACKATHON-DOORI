package com.doori.hackerthon.service;

import com.doori.hackerthon.dto.ExamDto;
import com.doori.hackerthon.dto.SummaryDto;
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
        questionList.add("Best Effort란 무엇을 의미하며, 어떤 종류의 통신 서비스를 설명하나요?");
        questionList.add("Best Effort 서비스의 주요 특징 중 하나는 무엇이며, 왜 이것이 중요한가요?");
        questionList.add("라우팅이란 무엇이며, 어떻게 작동하는지 간단히 설명하세요.");
        questionList.add("정적 라우팅은 무엇이고 장단점은 무엇인가요?");
        answerList.add("OSI 모델의 보안 기능은 주로 7번째 계층인 애플리케이션 계층에서 수행됩니다.");
        answerList.add("잘못된 답변입니다. MAC (Media Access Control) 주소를 사용하여 데이터 프레임을 관리하고 네트워크 내에서 전달하는 계층은 OSI 모델의 2번째 계층인 데이터 링크 계층입니다.");
        answerList.add("Best effort은 통신 네트워크에서 데이터 전달을 최선을 다해 시도하되, 데이터의 분실, 중복, 순서 변경 및 비트 오류와 같은 문제에 대한 보장을 제공하지 않는 비연결형 서비스를 의미합니다.");
        answerList.add("Best effort 서비스의 주요 특징 중 하나는 네트워크 리소스를 효율적으로 활용하여 대역폭을 최대한 확보하려는 노력입니다. 이것은 네트워크에서 다양한 유형의 트래픽이 경쟁하고, 모든 패킷이 최선을 다해 전달되려고 시도함을 의미합니다.");
        answerList.add("라우팅은 네트워크에서 패킷을 출발지에서 목적지로 전달하는 프로세스입니다. 라우터라고 불리는 네트워크 장비는 패킷의 목적지 주소를 확인하고 최적의 경로를 선택하여 패킷을 다음 라우터로 전송합니다. ");
        answerList.add("정적 라우팅은 라우터 관리자가 수동으로 경로를 설정하는 방법입니다. 라우터가 네트워크 변경 사항을 자동으로 감지하지 않으며 설정이 변경되지 않는 한 항상 동일한 경로를 사용합니다. 장점은 예측 가능하고 간단하며 안정적이지만 네트워크가 크게 확장되면 유지 관리가 어려울 수 있습니다.");

        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setQuestion(questionList);
        adminGptEntity.setAnswer(answerList);
        adminGptRepository.save(adminGptEntity);
    }

    public void saveRetest() {
        List<String> questionList = new ArrayList<>();
        List<String> answerList = new ArrayList<>();
        questionList.add("OSI 모델의 몇 번째 계층에서 데이터를 응용 프로그램과 상호 작용하도록 합니다?");
        questionList.add("어떤 OSI 계층에서 데이터의 에러 검출과 복구를 수행하나요?");
        questionList.add("동적 라우팅은 무엇인가요?");
        questionList.add("라우터가 라우팅 결정을 내리는 데 어떤 정보를 사용하나요?");

        answerList.add("7번째 계층, 애플리케이션 계층에서 데이터를 응용 프로그램과 상호 작용하도록 합니다.");
        answerList.add("잘못된 답변입니다. 에러 검출과 복구는 2번째 계층, 데이터 링크 계층에서 수행합니다.");
        answerList.add("라우터가 네트워크의 상태를 모니터링하고 변경 사항에 따라 최적의 경로를 선택하는 방법입니다. 동적 라우팅은 네트워크 변경 사항에 신속하게 대응하고 관리가 용이합니다.");
        answerList.add("라우터가 라우팅 결정을 내리는 데 사용하는 주요 정보에는 목적지 IP 주소, 라우터가 알고 있는 네트워크 토폴로지 정보, 경로 비용(메트릭), TTL(Time To Live) 등이 포함됩니다.");

        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setQuestion(questionList);
        adminGptEntity.setAnswer(answerList);
        adminGptRepository.save(adminGptEntity);
    }

    public void saveTdtest() {
        List<String> questionList = new ArrayList<>();
        List<String> answerList = new ArrayList<>();
        questionList.add("OSI 모델의 어떤 계층에서 IP 주소를 사용하여 데이터를 목적지로 전달하나요?");
        questionList.add("OSI 모델의 몇 번째 계층에서 패킷의 순서를 관리하고 전송 중에 재조립을 수행하나요?");

        answerList.add("3번째 계층, 네트워크 계층에서 IP 주소를 사용하여 데이터를 목적지로 전달합니다.");
        answerList.add("4번째 계층, 전송 계층에서 패킷의 순서를 관리하고 전송 중에 재조립을 수행합니다.");

        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setQuestion(questionList);
        adminGptEntity.setAnswer(answerList);
        adminGptRepository.save(adminGptEntity);
    }
    public void saveFirstSmry(){
        List<String> summaryList = new ArrayList<>();
        List<String> keywordList = new ArrayList<>();
        keywordList.add("Best Effort");
        keywordList.add("라우팅");
        keywordList.add("OSI 7계층");
        summaryList.add("Best Effort은 네트워크 통신에서 사용되는 서비스 형태 중 하나로, 데이터 전달의 최선을 다하지만 데이터 전송의 신뢰성이나 품질을 보장하지 않는 원칙을 의미합니다. Best Effort 서비스는 다음과 같은 특징을 갖습니다:" +
                "신뢰성 부족: Best Effort에서는 데이터 패킷을 최대한 빠르게 전송하려고 노력하지만, 데이터의 분실, 중복 전송, 순서 변경, 지연 등의 문제가 발생할 수 있습니다. 이러한 문제에 대한 복구 기능이나 재전송은 제공되지 않습니다." +
                "자원 효율성: Best Effort 서비스는 연결 설정 및 종료와 같은 추가 오버헤드 없이 데이터를 빠르게 전송하는 데 중점을 둡니다. 이로써 네트워크 자원을 효율적으로 사용할 수 있습니다." +
                "적용 분야: Best Effort는 실시간 데이터 스트리밍, 웹 페이지 로딩, 이메일 전송 등과 같이 신속한 데이터 전달이 중요한 응용 프로그램에 주로 사용됩니다. 하지만 응용 프로그램마다 데이터의 손실이나 순서 변경이 허용되는 정도가 다를 수 있습니다." +
                "전송 보장 없음: Best Effort에서는 데이터 패킷의 전송 여부나 도착 순서를 확인하지 않으며, 수신 측에서 패킷의 상태를 확인하거나 복구하는 역할을 하지 않습니다. 이런 역할은 상위 계층에서 처리해야 합니다." +
                "연결 설정 없음: Best Effort는 연결 설정과 종료 절차가 필요하지 않습니다. 따라서 연결형 서비스보다 빠른 데이터 전송이 가능합니다.");
        summaryList.add(
                "라우팅은 컴퓨터 네트워크에서 데이터 패킷이 출발지에서 목적지로 전달되는 경로를 결정하는 과정입니다. 이 과정은 데이터 패킷이 네트워크를 통해 어떻게 이동할지를 제어하고 관리합니다. 라우팅은 다음과 같은 주요 개념으로 설명됩니다:" +
                        "라우터: 라우터는 네트워크 장비로서 데이터 패킷을 받아들이고 패킷을 다음 경로로 전달하는 역할을 합니다. 라우터는 패킷의 목적지 주소를 분석하고, 패킷을 가장 적절한 출력 인터페이스로 전송합니다." +
                        "라우팅 알고리즘: 라우터가 라우팅 테이블을 작성하고 업데이트하는 데 사용하는 알고리즘입니다. 라우팅 알고리즘은 다양한 기준을 고려하여 최적의 경로를 선택하고 업데이트합니다." +
                        "정적 라우팅 vs. 동적 라우팅: 정적 라우팅은 관리자가 수동으로 라우팅 테이블을 설정하는 방식으로, 간단하고 예측 가능한 경로를 생성합니다. 동적 라우팅은 라우터 간에 라우팅 정보를 자동으로 교환하여 네트워크 상황에 따라 최적의 경로를 동적으로 조정합니다." +
                        "경로 선택 기준: 라우터는 패킷의 목적지 주소, 경로의 비용, 대역폭, 신뢰성 등 다양한 기준을 고려하여 경로를 선택합니다." +
                        "인터넷과 라우팅: 인터넷은 수많은 라우터로 구성되어 있으며, 데이터 패킷은 다중 라우터를 통해 목적지까지 전달됩니다. 각 라우터는 패킷을 받아 라우팅 테이블을 사용하여 다음 경로를 결정하고 패킷을 전달합니다.");
        summaryList.add("OSI 7계층 모델은 다음과 같은 계층으로 구성됩니다:" +
                "물리 계층:" +
                "물리적 매체을 통해 비트 스트림을 전송합니다." +
                "주요 기능: 비트 전송, 전압 및 시그널 규격, 물리적 연결 관리" +
                "데이터 링크 계층:" +
                "물리 계층에서 전송된 비트를 프레임으로 그룹화하고 오류 검출 및 수정을 수행합니다." +
                "주요 기능: 프레임 생성 및 검사, 흐름 제어, 오류 감지 및 수정" +
                "네트워크 계층:" +
                "데이터 패킷의 경로를 선택하고 라우팅을 수행하여 목적지까지 데이터 패킷을 전달합니다." +
                "주요 기능: 라우팅, 패킷 전달" +
                "전송 계층:" +
                "송신 호스트와 수신 호스트 간의 데이터 전달을 관리하며, 데이터의 정확성 제공합니다." +
                "주요 기능: 데이터 분할 및 재조립, 흐름 제어, 오류 복구" +
                "세션 계층:" +
                "통신 세션을 설정, 유지, 및 해제하며, 데이터 교환을 관리합니다." +
                "주요 기능: 세션 설정 및 유지, 대화 제어" +
                "표현 계층:" +
                "데이터 형식을 변환하고 압축, 암호화, 해독 등의 데이터 변환을 수행합니다." +
                "주요 기능: 데이터 형식 변환, 데이터 암호화, 압축" +
                "애플리케이션 계층:" +
                "최종 사용자 애플리케이션과 상호 작용하며 네트워크 서비스 및 응용 프로그램을 제공합니다." +
                "주요 기능: 사용자 인터페이스, 데이터 전송 및 응용 프로그램 지원");

        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summaryList);
        adminGptEntity.setKeyword(keywordList);
        adminGptRepository.save(adminGptEntity);
    }
    public List<SummaryDto> getFirstSmry(){
        AdminGptEntity adminGptEntity = adminGptRepository.findById(6L).get();
        List<String> summary = adminGptEntity.getSummary();
        List<String> keyword = adminGptEntity.getKeyword();
        List<SummaryDto> summaryList = new ArrayList<>();

        for(int i=0; i<3; i++)
            summaryList.add(new SummaryDto(keyword.get(i), summary.get(i)));

        return summaryList;
    }

    public void saveSummary() {
        List<String> questionList = new ArrayList<>();
        List<String> answerList = new ArrayList<>();
        List<String> keywordList = new ArrayList<>();
        List<String> summaryList = new ArrayList<>();
        questionList.add("Best Effort란 무엇을 의미하며, 어떤 종류의 통신 서비스를 설명하나요?");
        questionList.add("정적 라우팅은 무엇이고 장단점은 무엇인가요?");
        questionList.add("어떤 OSI 계층에서 데이터의 에러 검출과 복구를 수행하나요?");

        answerList.add("Best Effort은 통신 네트워크에서 데이터 전달을 최선을 다해 시도하되, 데이터의 분실, 중복, 순서 변경 및 비트 오류와 같은 문제에 대한 보장을 제공하지 않는 비연결형 서비스를 의미합니다.");
        answerList.add("정적 라우팅은 라우터 관리자가 수동으로 경로를 설정하는 방법입니다. 라우터가 네트워크 변경 사항을 자동으로 감지하지 않으며 설정이 변경되지 않는 한 항상 동일한 경로를 사용합니다. 장점은 예측 가능하고 간단하며 안정적이지만 네트워크가 크게 확장되면 유지 관리가 어려울 수 있습니다.");
        answerList.add("에러 검출과 복구는 2번째 계층, 데이터 링크 계층에서 수행합니다.");

        keywordList.add("Best Effort");
        keywordList.add("라우팅");
        keywordList.add("OSI 7계층");

        summaryList.add("라우팅 (Routing): 라우팅은 네트워크에서 데이터 패킷이 출발지에서 목적지로 전달되는 경로를 결정하는 프로세스를 의미합니다. 데이터 패킷은 다수의 라우터를 거쳐 목적지로 전달됩니다. 각 라우터는 패킷의 목적지 주소를 기반으로 최적의 경로를 선택하고, 라우팅 테이블을 사용하여 패킷을 다음 경로로 전달합니다. 라우팅은 네트워크에서 데이터 흐름을 관리하고 효율적인 통신을 지원하는 핵심 프로세스입니다.");
        summaryList.add("Best Effort: 네트워크 통신에서 데이터 패킷의 전달을 최선을 다해 시도하되, 패킷의 분실, 중복 전달, 패킷 순서 변경, 비트 오류 등이 발생할 경우에도 패킷 재전송이나 오류 확인 등의 기능을 제공하지 않는 통신 방식을 의미합니다. 이것은 IP 프로토콜의 특성 중 하나이며, IP는 데이터 패킷을 최대한 빠르게 전달하는 것을 목표로 하며, 데이터 신뢰성 및 오류 복구는 상위 계층에서 처리합니다.");
        summaryList.add("OSI 7계층: OSI 모델은 컴퓨터 네트워크에서 통신 프로토콜의 기능을 7개의 계층으로 나눈 모델입니다. 이 모델은 네트워크 통신을 구조화하고 표준화하기 위해 개발되었습니다. 각 계층은 특정한 역할을 수행하며, 위에서 아래로 데이터를 전달하면서 네트워크 통신을 관리합니다. OSI 모델의 각 계층은 다음과 같습니다: 물리 계층, 데이터 링크 계층, 네트워크 계층, 전송 계층, 세션 계층, 표현 계층, 응용 계층");


        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setQuestion(questionList);
        adminGptEntity.setAnswer(answerList);
        adminGptEntity.setSummary(summaryList);
        adminGptEntity.setKeyword(keywordList);
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
        keywordList.add("OSI 7계층");
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

    public List<ExamDto> getExam(Long id) {
        AdminGptEntity adminGptEntity = adminGptRepository.findById(id).get();
        List<String> questionList = adminGptEntity.getQuestion();
        List<String> answerList = adminGptEntity.getAnswer();


        List<ExamDto> examDtos = new ArrayList<>();
        for (int i = 1; i <= questionList.size(); i++) {
            if (id == 2 && i == 2 || i == 6)
                examDtos.add(new ExamDto(i, questionList.get(i - 1), answerList.get(i - 1), false));
            else if (id == 3 && i == 2)
                examDtos.add(new ExamDto(i, questionList.get(i - 1), answerList.get(i - 1), false));
            else
                examDtos.add(new ExamDto(i, questionList.get(i - 1), answerList.get(i - 1), true));
        }
        return examDtos;
    }

    public List<SummaryDto> getSummary() {
        AdminGptEntity adminGptEntity = adminGptRepository.findById(5L).get();

        List<String> keyword = adminGptEntity.getKeyword();
        List<String> summary = adminGptEntity.getSummary();
        List<String> question = adminGptEntity.getQuestion();
        List<String> answer = adminGptEntity.getAnswer();
        System.out.println(keyword.get(0));
        System.out.println(summary.get(0));
        List<SummaryDto> summaryDtoList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SummaryDto summaryDto = new SummaryDto(i + 1, question.get(i), answer.get(i), keyword.get(i), summary.get(i));
            summaryDtoList.add(summaryDto);
        }


        return summaryDtoList;
    }
}
