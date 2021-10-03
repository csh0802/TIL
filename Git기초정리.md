# Git 초기설정

## 1. 폴더 생성 후 GIT BATH 실행

폴더를 생성한 후 마우스 우측클릭 후 여기에서 GIT BATH실행을 눌러서 GIT BATH를 실행한다.

<img src="C:\Users\tjdgh\AppData\Roaming\Typora\typora-user-images\image-20210927144837482.png" alt="image-20210927144837482" style="zoom:80%;" />

## 2. Git 환경설정

### Config

깃에  그냥 git commit만 입력하면 Vim환경 나타남

- Vim : CLI 텍스트 편집, 마우스가 필요 없는 환경

복잡할 수 있으므로 VS코드로 연결해줄 필요성이 있음

```bash
$ git config --global core.editor "code --wait"
```

### gitignore

> git으로 관리하지 않을 파일 목록

```bash
# 특정 파일
data.csv
# 특정 폴더
secret/
# 특정 확장자 (*)
*.csv
```

- 

## 3. Git Init 및 파일 생성

git init 명령어 입력을 통해 git repository를 생성 후 ls -al 명령어를 입력한다.

```bash
$ git init
Initialized empty Git repository in
c:/users/tjdgh/OneDrive/바탕화면/tt/.git/
$ ls _al
```

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



# 원격저장소와 통신

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

### 원격저장소 `pull`

> 원격저장소와 Working Directory의 파일이 다른 상태에서 push를 하면 오류가 발생, 이를 해결하기 위해 pull 사용

```bash
$ git pull origin master
remote: Enumerating objects: 4, done.
remote: Counting objects: 100% (4/4), done.
remote: Compressing objects: 100% (3/3), done.
remote: Total 3 (delta 1), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (3/3), 2.04 KiB | 208.00 KiB/s, done.
From https://github.com/edutak/0928
 * branch            master     -> FETCH_HEAD
   2a71213..c143740  master     -> origin/master
hint: Waiting for your editor to close the fiMerge made by the 'recursive' strategy.
 22.md | 159 ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 1 file changed, 159 insertions(+)
 create mode 100644 22.md
```

- 커밋 메시지(merge)가 발생

```bash
$ git log --oneline
9f0d621 (HEAD -> master) Merge branch 'master' of https://github.com/edutak/0928
772310c Add d.txt
c143740 (origin/master) Add files via upload
2a71213 Update

```

- 충돌 발생시 해결이 필요

### 원격저장소 목록

```bash
$ git remote -v
```

### 원격저장소 복제

> git 저장소를 복제

```bash
$ git clone [URL]
```

- Git 저장소 URL을 입력하면 그 저장소 전체를 대상 폴더에 복제



# Undoing

### 1) add취소

```bash
$ git rm [file_name] 		  #원격 및 로컬 저장소의 파일 삭제
$ git rm --cached [file_name] #원격 저장소의 파일만 삭제
```

- 파일을 untracked상태로 만듦

```bash
$ git restore --staged [file_name]#staging상태의 파일을 unstage로 만듦
```

### 2) 이전 버전으로 되돌리기

```bash
$ git restore [file_name] #WD에 있는 파일을 내림(파일 변경사항 삭제)
```

- 해당 명령어 실행 후 절대로 다시 복원 불가능

### 3) 커밋 메시지 수정

> 공개된 저장소에 push 된 커밋은 절대 수정불가 why? 해쉬값이 변경되기 때문 -> 충돌 위험 존재
>
> staging -> 메시지 -> commit완료의 과정중에 메시지 전 단계로 감

```bash
$ git commit --amend
```

- visual studio code나 vim 등 커밋 메시지 작성 화면이 나온다.
- 여기에서 수정하고 저장하면 반영된다.
- 이 명령어를 이용해서 빠진 파일을 추가해서 커밋할 수 있다.
  - 빠진파일을 add를 통해 올려놓은 다음 위의 명령어를 사용하면 빠진파일과 원래 커밋되었던 파일이 같이 올라가면서 commit 됨

```bash
$ git status
On branch master
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   real.txt

Untracked files:
  (use "git add <file>..." to include in what will be committed)
        omit.txt


student@M503INS MINGW64 ~/Desktop/last (master)
$ git commit -m 'omit & real'
[master ce16ecb] omit & real
 1 file changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 real.txt

student@M503INS MINGW64 ~/Desktop/last (master)
$ git status
On branch master
Untracked files:
  (use "git add <file>..." to include in what will be committed)
        omit.txt

nothing added to commit but untracked files present (use "git add" to track)

 # 해결

$ git add .
$ git status
On branch master
Changes to be committed:
  (use "git restore --staged <file>..." to unstage)
        new file:   omit.txt
$ git commit --amend
hint: Waiting for your editor to close the file..[master ce77c2d] omit & real
 Date: Fri Jun 4 17:29:22 2021 +0900
 2 files changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 omit.txt
 create mode 100644 real.txt
```

### 4) 커밋 되돌리기

> reset은 입력한 커밋 해쉬값으로 돌아가면서 커밋기록도 삭제, revert는 특정사건만 없애고 revert한 이력을 커밋에 남김

``` bash
$ git reset [커밋해쉬값]
```

* `--hard` : reset 하기전 SA, WD 모든 작업 리셋
* `--mixed` : (기본) SA reset. WD 작업은 남겨둠
* `--soft` : reset 하기전까지 했던 SA, WD 작업은 남겨둠

```bash
$ git revert d547a8a
```



# Git Flow

> Git을 활용해서 협업하는 흐름으로 branch를 활용하는 전략

## 준비

> 반드시 Root commit이 있는 상태에서 브랜치를 조작해야 함

* 폴더를 만들고, README.md 파일을 만든 후 커밋

### 상황 1. fast-foward

> fast-foward는 feature 브랜치 생성된 이후 master 브랜치에 변경 사항이 없는 상황

1. feature/index branch 생성 및 이동

   ```bash
   $ git branch feature/index
   $ git checkout feature/index
   (feature/index) $
   ```

2. 작업 완료 후 commit

   ```bash
   $ touch index.html # index.html
   $ git add . 
   $ git commit -m 'Complete index page'
   [feature/index 552a702] Complete index page
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 index.html
   $ git log --oneline
   552a702 (HEAD -> feature/index) Complete index page
   a7f5099 (master) Add README
   ```


3. master 이동

   ```bash
   (feature/index) $ git checkout master
   Switched to branch 'master'
   ```


4. master에 병합

   ```bash
   $ git log --oneline
   a7f5099 (HEAD -> master) Add README
   $ git merge feature/index
   Updating a7f5099..552a702
   # fast-forward
   Fast-forward
    index.html | 0
    1 file changed, 0 insertions(+), 0 deletions(-)
    create mode 100644 index.html
   ```


5. 결과 -> fast-foward (단순히 HEAD를 이동)

   ```bash
   $ git log --oneline
   552a702 (HEAD -> master, feature/index) Complete index page
   a7f5099 Add README
   ```

6. branch 삭제

   ```bash
   $ git branch -d feature/index
   Deleted branch feature/index (was 552a702)
   ```

---

### 상황 2. merge commit

> 서로 다른 이력(commit)을 병합(merge)하는 과정에서 다른 파일이 수정되어 있는 상황
>
> git이 auto merging을 진행하고, commit이 발생된다.

1. feature/style branch 생성 및 이동

   ```bash
   $ git checkout -b feature/style
   Switched to a new branch 'feature/style'
   (feature/style) $
   ```

2. 작업 완료 후 commit

   ```bash
   $ touch style.css
   $ git add .
   $ git commit -m 'Complete style'
   $ git log --oneline
   420c551 (HEAD -> feature/style) Complete style
   552a702 (master) Complete index page
   a7f5099 Add README
   ```

3. master 이동

   ```bash
   $ git checkout master
   Switched to branch 'master'
   ```

4. *master에 추가 commit 발생시키기!!*

   * **다른 파일을 수정 혹은 생성!**

   ```bash
   $ touch hotfix.html
   $ git add .
   $ git commit -m 'Hotfix'
   $ git log --oneline
   565e160 (HEAD -> master) Hotfix!
   552a702 Complete index page
   a7f5099 Add README
   ```

5. master에 병합

   ```bash
   $ git merge feature/style
   ```

6. 결과 -> 자동으로 *merge commit 발생*

   * 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하고 엔터를 눌러서 저장 및 종료
     * `w` : write
     * `q` : quit

   * 혹은 visual studio code 라면, 저장후 창을 종료

7. 그래프 확인하기

   ```bash
   $ git log --graph --oneline
   *   33689bb (HEAD -> master) Merge branch 'feature/style'
   |\
   | * 420c551 (feature/style) Complete style
   * | 565e160 Hotfix!
   |/
   * 552a702 Complete index page
   * a7f5099 Add README
   ```

8. branch 삭제

   ```bash
   $ git branch -d feature/style
   Deleted branch feature/style (was 420c551).
   ```

   

---

### 상황 3. merge commit 충돌

> 서로 다른 이력(commit)을 병합(merge)하는 과정에서 동일 파일이 수정되어 있는 상황
>
> git이 auto merging을 하지 못하고, 해당 파일의 위치에 라벨링을 해준다.
>
> 원하는 형태의 코드로 직접 수정을 하고 merge commit을 발생 시켜야 한다.

1. feature/about branch 생성 및 이동

   ```bash
   $ git checkout -b feature/about
   Switched to a new branch 'feature/about'
   ```

2. 작업 완료 후 commit *추가적으로 README.md에 내용을 추가해주세요*

   ```bash
   $ touch about.html
   # README 열어서 수정하고 저장!
   $ git status
   # README 고치고 추가 파일도 있고!!!!!
   On branch feature/about
   Changes not staged for commit:
     (use "git add <file>..." to update what will be committed)
     (use "git restore <file>..." to discard changes in working directory)
           modified:   README.md
   
   Untracked files:
     (use "git add <file>..." to include in what will be committed)
           about.html
   
   no changes added to commit (use "git add" and/or "git commit -a")
   
   ```

   ```bash
   $ git add .
   $ git commit -m 'Update about and README'
   $ git log --oneline
   3215402 (HEAD -> feature/about) Update about and README
   33689bb (master) Merge branch 'feature/style'
   565e160 Hotfix!
   420c551 Complete style
   552a702 Complete index page
   a7f5099 Add README
   
   ```


3. master 이동

   ```bash
   $ git checkout master
   ```

   


4. *master에 추가 commit 발생시키기!!*

   * **동일 파일을 수정 혹은 생성(README에 내용을 아까와 다르게 작성) **

   ```bash
   $ git status
   On branch master
   Changes not staged for commit:
     (use "git add <file>..." to update what will be committed)
     (use "git restore <file>..." to discard changes in working directory)
           modified:   README.md
   
   no changes added to commit (use "git add" and/or "git commit -a")
   
   ```

   ```bash
   $ git add .
   $ git commit -m 'Update README'
   $ git log --oneline
   792a392 (HEAD -> master) Update README
   33689bb Merge branch 'feature/style'
   565e160 Hotfix!
   420c551 Complete style
   552a702 Complete index page
   a7f5099 Add README
   
   ```

   

5. master에 병합

   ```bash
   $ git merge feature/about
   Auto-merging README.md
   # Merge conflict => README.md
   CONFLICT (content): Merge conflict in README.md
   # conflict를 고치고 커밋을 해라..!
   Automatic merge failed; fix conflicts and then commit the result.
   (master | MERGING) $
   ```

   


6. 결과 -> *merge conflict발생*

   ```bash
   $ git status
   On branch master
   You have unmerged paths.
     (fix conflicts and run "git commit")
     (use "git merge --abort" to abort the merge)
   
   Changes to be committed:
           new file:   about.html
   
   Unmerged paths:
     (use "git add <file>..." to mark resolution)
           both modified:   README.md
   
   ```


7. 충돌 확인 및 해결

   ```bash
   >>>>>>>>>>>> HEAD
   r12121
   ===============
   asdsf
   <<<<<<<<<<< feature/about
   ```

   ```bash
   $ git status
   On branch master
   All conflicts fixed but you are still merging.
     (use "git commit" to conclude merge)
   
   Changes to be committed:
           modified:   README.md
           new file:   about.html
   
   ```


8. merge commit 진행

   ```bash
   $ git commit
   ```

   * vim 편집기 화면이 나타남

   * 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료
     * `w` : write
     * `q` : quit

9. 그래프 확인하기

   ```bash
   $ git log --oneline --graph
   *   83b7fbd (HEAD -> master) Merge branch 'feature/about'
   |\
   | * 3215402 (feature/about) Update about and README
   * | 792a392 Update README
   |/
   *   33689bb Merge branch 'feature/style'
   |\
   | * 420c551 Complete style
   * | 565e160 Hotfix!
   |/
   * 552a702 Complete index page
   * a7f5099 Add README
   
   ```


10. branch 삭제

    ```bash
    $ git branch -d feature/about
    ```

    











