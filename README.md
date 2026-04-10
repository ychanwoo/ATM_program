# Bank ATM Simulator

Java로 구현한 콘솔 기반 ATM 프로그램입니다.  
신한은행 ATM의 실제 서비스 흐름을 분석하여 **객체지향 설계(OOP)** 원칙에 따라 컴포넌트 구조로 분리하여 구현했습니다.

## 🚀 Key Features

- **예금/출금**: 만원 단위 입출금 및 잔액 확인
- **입금/무통장입금**: 본인 계좌 및 타인 계좌 무통장 입금 지원
- **계좌이체**: 사용자 간 실시간 이체 기능 (자기 계좌 이체 방지 로직 포함)
- **통장정리**: 거래 내역 기록 및 조회 (`Transaction` 클래스 활용)
- **예금조회**: 현재 잔액 및 계좌 상태 확인
- **장기카드대출**: `LoanAccount`를 통한 한도 내 마이너스 출금 지원 (**상속 및 오버라이딩 적용**)
- **캐시비 / 하이패스**: 교통카드 및 하이패스 잔액 관리 모듈
- **언어설정 (Foreign Languages)**: 다국어(KOR/ENG) UI 대응 (`LanguageManager`)

## 🛠 Tech Stack

- **Language**: Java 17 (or higher)
- **UI**: Console / Terminal UI (CLI)
- **Design Pattern**: Object-Oriented Programming (Encapsulation, Inheritance, Polymorphism)
- **Workflow**: GitHub Issues / Branch / PR Workflow 기반 형상 관리

## 📂 Project Structure

```text
ATM_program
├── bin                  # 컴파일된 클래스 파일 (.class)
│   ├── App.class
│   └── components
│       ├── ATMService.class
│       ├── Account.class
│       ├── BankData.class
│       ├── Cashbee.class
│       ├── LanguageManager.class
│       ├── LoanAccount.class
│       └── Transaction.class
├── src                  # 소스 코드 (.java)
│   ├── App.java         # 메인 진입점 및 메뉴 컨트롤러
│   └── components       # 기능별 모듈화된 컴포넌트
│       ├── ATMService.java
│       ├── Account.java
│       ├── BankData.java
│       ├── Cashbee.java
│       ├── LanguageManager.java
│       ├── LoanAccount.java
│       └── Transaction.java
└── README.md
```

## 🏗 Main Classes & Design

| 클래스명                 | 역할 설명                                                           |
| :----------------------- | :------------------------------------------------------------------ |
| **App.java**             | 프로그램의 Entry Point. 메인 메뉴 출력과 전체적인 흐름 제어         |
| **ATMService.java**      | 로그인 검증, 송금 알고리즘 등 핵심 비즈니스 로직 처리               |
| **BankData.java**        | `Account[]` 배열을 통해 사용자 데이터를 관리하는 가상 저장소        |
| **Account.java**         | 기본 계좌 모델                                                      |
| **LoanAccount.java**     | `Account`를 상속받아 대출 한도 내 마이너스 출금 로직으로 오버라이딩 |
| **Transaction.java**     | 개별 거래 정보를 객체화하여 리스트 형식으로 저장                    |
| **LanguageManager.java** | 메뉴 및 시스템 메시지의 다국어 지원 엔진                            |

## 🧪 Validation Rules

- **금액 제한**: 모든 입/출금은 0원보다 커야 하며, 만원 단위만 허용됩니다.
- **보안**: 로그인 비밀번호 오류는 최대 3회까지만 허용됩니다.
- **무결성**: 자기 자신에게 계좌이체는 불가능하도록 차단되어 있습니다.
- **특수 로직**: 대출 계좌(`LoanAccount`)는 설정된 한도 내에서 잔액이 0원 미만이어도 출금이 가능합니다.

## 💻 How to Run

**컴파일 및 실행**

```bash
javac -d bin src/App.java src/components/*.java
java -cp bin App
```
