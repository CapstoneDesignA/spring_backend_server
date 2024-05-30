## 콘솔 창에서 스프링부트 띄우기 (윈도우 기준)
1. `git clone` 후 프로젝트로 이동 (`cd` 명령어)
2. `dir` 명령어로 폴더 내에 `gradlew` 파일있는지 확인
3. `gradlew build` 명령어로 빌드
- `gradlew clean build -x test` 명령어로 테스트 코드 실행 없이 빌드
4. `/build/libs` 폴더로 이동해서 빌드된 jar 파일 확인
5. `java -jar `빌드 파일 이름.jar`로 스프링부트 실행
