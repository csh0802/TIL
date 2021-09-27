# Git 초기설정

## 1. 폴더 생성 후 GIT BATH 실행

폴더를 생성한 후 마우스 우측클릭 후 여기에서 GIT BATH실행을 눌러서 GIT BATH를 실행한다.

<img src="C:\Users\tjdgh\AppData\Roaming\Typora\typora-user-images\image-20210927144837482.png" alt="image-20210927144837482" style="zoom:80%;" />

## 2. Git Init

git init 명령어 입력을 통해 git repository를 생성 후 ls -al 명령어를 입력한다.

```bash
$ git init
Initialized empty Git repository in
c:/users/tjdgh/OneDrive/바탕화면/tt/.git/
$ ls _al
```



## 3. 파일 생성

commit 및 버전관리 실습을 수행하고자 a.txt파일을 생성 한다.

```bash
$ touch README.md
#파일 생성
$ git status
On branch master

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        README.md

nothing added to commit but untracked files present (use "git add" to track)
# git add . 를 사용하면 해결 됨
$ git status
On branch master
Changes to be committed:
#commit될 파일들 목록
  (use "git restore --staged <file>..." to unstage)
        new file:   README.md
#입력 후 status 입력 결과 
```



## 4. Git Add

working directory상의 변경 내용을 staging area에 추가하기 위해 사용

- untracked 상태의 파일을 staged로 변경 

-  modified 상태의 파일을 staged로 변경

  ```bash
  $ git add <file>
  $ git add . 			#현재 디렉토리
  $ git add a.txt b.txt	#특정 파일
  $ git add my_folder/	#특정 폴더
  $ git add *.md			#특정 확장자
  ```

  

## 5. Git Commit

staged 상태의 파일들을 커밋을 통해 버전으로 기록



- commit

```bash
$ git commit # 메시지 편집 창(기본 Vim)
$ git commit -m '메시지'
[master e499b1d] 메시지
 1 file changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 README.md

```



## 6. git status, git log로 버전 관리내역 확인

- ### `status`

> Working directory(untracked, Changes not staged for commit), Staging area(Changes to be committed)의 상태를 보여줌. 

```bash
$ git status
On branch master
nothing to commit, working tree clean
```

- ### `log`

> commit을 조회 

```bash
$ git log # 전체 
commit 53dc80f4be3fa19e749ab6a8558e4b98c8f12717 (HEAD -> master)
Author: edutak <edutak.ssafy@gmail.com>
Date:   Mon Sep 27 15:12:11 2021 +0900

    Add README.md

$ git log -2 # 최근 2개
$ git log --oneline # 간략하게
53dc80f (HEAD -> master) Add README.md

$ git log --oneline -2 # 최근 2개를 간략하게
```

## 7. git push

### 원격저장소 등록

```bash
$ git remote add origin http://github.com/username/repository_name.git
```

- `origin`이름으로 특정 URL을 등록
  - 깃아 원격저장소(`remote`)`추가`해줘 `origin`이름으로 `URL`을
- 오류 메시지  : 특정 이름으로 이미 등록된 경우 에러가 발생
  - URL변경(`set url`) 혹은 해당 원격저장소를 삭제하고 다시 등록 필요

```bash
#기존 URL을 잘못 지정
$ git remote add origin http://github.com/username/repository_name.git
error : remote origin already exists. #덮어쓰기 x
$ git remote rm origin # 삭제
$ git remote add origin http://github.com/username/repository_name.git # 다시
```

### 원격저장소 `push`

```bash
$ git push origin master
```

- `origin`으로 지정된 원격저장서의 `master`로 `push`
- 오류메시지 : 커밋 혹은 브랜치 없는 경우

```bash
$ git push origin master
error: src refspec master does not match any
error: failed to push some refs to 'https://github.com/username/test.git'
```

### 원격저장소 목록

```bash
$ git remote -v
```



