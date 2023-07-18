# 1 Git简介

Git是分布式版本控制系统（Distributed Version Control System，简称 DVCS），远胜于SVN的代码版本管理方案。SVN是集中式版本控制系统，版本库是集中放在中央服务器。

官网：https://git-scm.com/

## 1.1代码托管中心

代码托管中心是基于网络服务器的远程代码仓库，一般我们简单称为远程库。

- 局域网
  - GitLab
- 互联网
  - GitHub（服务器在国外）
  - Gitee，也称为码云（国内网站）





# 2 Git使用

## 2.1配置全局用户签名

Git是分布式版本控制系统，所以需要配置用户名和邮箱作为一个唯一标识，即用户签名。

为了区分不同操作者身份。用户的签名信息在每一个版本的提交信息中能够看到，以此确认本次提交是谁做的。

Git首次安装必须设置一下用户签名，否则无法提交代码。

配置签名命令：

（1）配置用户名

```shell
git config --global user.name lideshui
```

（2）配置邮箱

```shell
git config --global user.email 512354665@qq.com
```

（3）使用命令查看配置信息

```shell
git config --list
```

（4）或者不使用命令，直接在当前用户目录下编辑隐藏文件`.gitconfig`

配置步骤：个项目设置用户签名把 --global去掉即可。

注意：这里设置用户签名和将来登录GitHub（或其他代码托管中心）的账号没有任何关系。



## 2.2核心概念

- 工作区(Working Directory)
  - 就是你电脑本地硬盘目录，一般是项目当前目录
- 暂存区(stage)
  - 存放在.git目录下的index文件，我们把暂存区有时也叫作索引（index）。
- 版本库(Repository)
  - 工作区里的隐藏目录.git，它就是Git的版本库（也称为本地库）。



## 2.3常用命令

| 命令名称                        | 命令作用                     |
| ------------------------------- | ---------------------------- |
| git init                        | 初始化本地库                 |
| git status                      | 查看本地库状态               |
| git checkout -- 文件名          | 将未添加到暂存区的改变删除   |
| git add 文件名                  | 将文件添加到暂存区           |
| git commit -m "提交信息" 文件名 | 将文件提交到本地库           |
| git log                         | 查看日志                     |
| git log --pretty=oneline        | 在一行显示日志               |
| git reflog                      | 查看引用日志，即历史记录     |
| git reset --hard HEAD^          | 回退一个版本                 |
| git reset --hard HEAD~n         | 回退n个版本                  |
| git reset --hard 版本号         | 版本穿梭                     |
| git branch -v                   | 查看分支                     |
| git branch 分支名               | 创建分支                     |
| git checkout 分支名             | 切换分支                     |
| git branch -d 分支名            | 删除分支                     |
| git merge 分支名                | 把指定的分支合并到当前分支上 |



## 2.4使用本地仓库

（1）创建目录

（2）在目录内执行git init命令，执行后会创建.git文件夹。config文件中有签名信息，HEAD文件中有分支信息

（3）首次创建文件后作出修改，可直接先执行git add，再执行git commit进行提交

```shell
#查看本地库状态
git status
```

```shell
#将所有代码提交到暂存区
git add *
```

```shell
#从本地库中删除所有代码
git checkou *
```

```shell
#将代码提交到本地库
git commit -m "创建了文件，并修改了文件内容"
```

（4）第二次再修改时执行git commit -am "修改说明"进行提交工作区内容（刚创建的东西不能使用-am提交）

```shell
git commit -am "修改了文件内容"
```

（5）然后还可以查看提交的版本信息

```shell
git log
```

```shell
git log --pretty=oneline
```

（6）查看自己的历史操作记录

```shell
git reflog
```



## 2.5版本控制

以当前版本为基础，回退上一个版本

```shell
git reset --hard HEAD^
```

以当前版本为基础，回退n个版本

```shell
git reset --hard HEAD~n
```

穿梭到指定版本，需要指定版本号

```shell
git reset --hard 0587b9
```



## 2.6使用分支

项目初始化之后，默认会有一个主干分支master，创建其他分支相当于把当前master分支复制了一份做自己的专属修改

切换分区的时候要保证工作区是干净的⚠️

查看分支

```shell
git branch -v
```

创建分支

```shell
git branch test
```

切换分支，切换后HEAD文件的内容会修改，版本的指针会指向test⚠️

```shell
git checkout test
```

删除分支，删除前不能在当前分支内删除该分支

```shell
git branch -d test
```

合并其他分支代码到当前分支

```shell
git merge test
```



## 2.7合并冲突

合并分支时，两个分支在同一个文件的同一个位置有两套完全不同的修改，就会产生冲突⚠️

错误信息：`Automatic merge failed; fix conflicts and then commit the result.`

查看冲突信息

```shell
git diff
```

查看冲突信息后，根据信息修改冲突内容，再次合并

```shell
git merge test
```

- 容易产生冲突的操作
  - 多个人同时操作了同一个文件
  - 一个人一直写不提交
  - 修改之前不更新最新代码
  - 擅自修改同事代码
- 减少冲突的操作
  - 养成良好的操作习惯，先`pull`再修改,修改完立即`commit`和`push`
  - 一定要确保自己正在修改的文件是最新版本的
  - 各自开发各自的模块
  - 如果要修改公共文件，一定要先确认有没有人正在修改
  - 下班前一定要提交代码，上班第一件事拉取最新代码
  - 一定不要擅自修改同事的代码

总结：上班前先pull最新的代码，写一个功能push一个功能，下班前一定要记得push





# 3 远程仓库

## 3.1GitHub和GitEE的使用

### 3.1.1GitHub和GitEE常用命令

| 命令名称                             | 命令作用                             |
| ------------------------------------ | ------------------------------------ |
| git remote add 别名 远程库地址       | 给远程仓库起别名                     |
| git push 别名 分支名                 | 将本地库某个分支中的内容推送到远程库 |
| git clone 远程地址 <本地库名字>      | 克隆远程库到本地                     |
| git pull 远程库地址别名 远程库分支名 | 拉取远程库分支内容到本地库           |



### 3.1.2GitHub和GitEE的使用

使用gitee创建空仓库后，不要点击`初始化readme文件`，不然就无法推送仓库到该远端仓库了⚠️

（1）首先创建本地仓库的目录，并进入该目录进行git初始化，设置该仓库的用户名和密码

```shell
git init
```

```shell
git config user.name lideshui
```

```shell
git config user.email 512354665@qq.com
```

根据地址起别名，不然每次提交修改都要输入仓库地址，随意起别名，这里以dog为例，后期push直接`push 别名`即可

```shell
git remote add dog https://gitee.com/lideshui/test
```

（2）提交代码

```shell
git add *
```

```shell
git commit -m "change code"
```

（3）推送仓库到该远端仓库

先对仓库地址起别名，后期push直接`push 别名`即可，别名随意起，这里以dog为例

```shell
git remote add dog https://gitee.com/lideshui/test
```

第一次push代码时需要输入用户名和密码，会生成凭据，可在凭据管理器中查看，包含用户名和密码，第二次及以后就不需要推了。

```shell
#这里以推送master分支为例
git push dog master
```

（4）克隆项目世界使用地址clone即可，克隆时可改变仓库别名，不然项目目录就以仓库名称命名，只可以clone公开的项目

```shell
git clone dog https://gitee.com/lideshui/test test2
```

（5）其他人克隆项目后无法修改自己上传的代码，需仓库拥有者邀请才可以修改，修改完成后分别提交到暂存区、本地库、云仓库

```shell
git add *
```

```shell
git commit -m "change code"
```

```shell
#若无新建的文件，前两步合并即可
git commit -am "change code"
```

```java
git push
```



## 3.2跨团队协作

当在自己项目内使用其他团队的开源github仓库时，可直接插入使用

### 3.2.1跨团队协作方式

根据需求搜索项目，找到合适的项目之后，点击右上角Fork（刀叉标志）及可使用。

当仓库的管理者们进行开发修改更新时，使用者们点击该仓库名称右边更新标志按钮，即可同步更新



### 3.2.2提交修改请求

点击自己Fork的项目，修改后，点击Pull Request即可向仓库管理者发起pull代码的请求

仓库管理者可对请求进行审核及评论



## 3.3SSH免密登录

通过https模式推送代码需要进行账号和密码授权或使用token。（使用凭据管理器其实也可以实现）

ssh模式比https模式的一个重要好处就是，每次push操作时不用重复填写账号和密码。前提是你必须是这个项目的拥有者或合作者，且配好了ssh key。

（1）配置ssh key的命令：ssh-keygen -t rsa -C 注释，注释随便写，在任意目录下执行即可

```shell
ssh-keygen -t rsa -C "lideshui"
```

注意：一台电脑只能授权一个账户免密登录，连续回车后生成.ssh文件夹，以及文件id_rsa(私钥)和id_rsa.pub(公钥)。

（2）复制id_rsa.pub(公钥)的内容，配到账号的公钥中即可。

注意：配置后要使用ssh的地址上传代码！并且一个账号可配置多个公钥，一般一个设备对应一台⚠️

（3）重新使用SSH协议克隆仓库，再上传则可以实现免密登录，或者修改别名的地址，然后修改代码提交后push上传

```shell
git@github.com:lideshui/shf.git
```

```
git push dog master
```

注意：其实可以不使用别名和分支，只是默认为master分支上传代码。





# 4 IDEA集成Git

## 4.1定位Git程序

点击Setting，点击Version Control，点击Git，点击Path to Git executable选择git安装目录：

输入命令查看git安装目录

```shell
which git
#git默认安装目录如下
/usr/local/bin/git
```

点击Test按钮测试，下方显示Git的版本，则定位成功



## 4.2配置忽略文件

### 4.2.1配置项目忽略文件

在项目根目录下创建.gitignore文件，使用这种方式每个项目都要单独创建，而且需要将该文件添加到本地库中，编写忽略规则

```sh
# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
hs_err_pid*

.classpath
.project
.settings
target
.idea
*.iml
```



### 4.2.2配置全局忽略文件

创建忽略规则文件.gitignore，该文件放在那里都可以， 建议与.gitconfig放在同一个目录下，在.gitconfig文件中引用忽略规则文件

```sh
[user]
	name = 韩总
	email = hanzong188@gmail.com
[core]
	excludesfile = /Users/lideshui/download..gitignore
```



## 4.3初始化本地库

点击工具栏VCS，再点击Create Git Repository，选择刚刚创建的工程的目录进行初始化

初始化之后，被忽略的文件和刚创建的文件都变了颜色，项目根目录也生成了.git目录



## 4.4添加到暂存区

新建一个Java文件，发现文件变成了橙色，同时弹出一个提示框提示是否添加到暂存区，如果点击Add只会将当前Java文件添加到暂存区，暂时点击Cancel不添加到暂存区，最终添加到暂缓区的文件会变成绿色

- 对文件操作：在文件上右键，找到Git选项，点击Add将文件添加到暂存区
- 对目录操作：在目录上右键，找到Git选项，点击Add将目录下的所有文件添加到暂存区



## 4.5提交到本地仓库

### 4.5.1在文件或项目目录上提交

（1）在文件或项目上右键，找到Git选项，点击Commit File…或Commit Directory…

（2）写提交信息并点击Commit按钮

（3）提交成功，生成版本之后文件变成了正常的黑色

（4）每次修改了文件也可以直接进行提交到本地库操作，只要保证提交时文件被勾选了就行



### 4.5.2点击工具栏按钮提交

点击工具类上的绿色√进行commit。

创建了新文件需要选中⚠️



## 4.6版本回退

点击Idea左下角的Git，点击Log可以看到提交的所有版本

选中要回退的版本，右键点击Reset Current Branch to Here…

选择回退类型

- Soft（软回退）：不同的内容比如之前创建的文件等都还在，只是会被保存到暂存区
- Hard（硬回退）：不同不会被保存到暂存区，本地的改变将会丢失（有些新版本的IDEA会保存在工作区）



## 4.7创建分支

- 方式一：点击右键，点击Git，点击Braches...
- 方式二：右下角点击New Branch，一旦创建成功将自动切换到该分支

现在所在的分支的选项：重命名、Push

其他的分支的选项：切换到该分支、重命名、Push



## 4.8合并分支

先对dev分支作出修改，并commit提交到本地库

- 合并方式一：先切换至master分支，再点击dev分支，再点击`Merge dev into master`，将dev分支内容合并到master主分支中
  - 注意：是先切换到master分支再点击dev分支合并，合并时显示内容 `Merge dev into master`（将dev分支合并到master）⚠️
- 合并方式二：点击右键，点击Git，点击Braches...，再点击dev分支，再点击`Merge dev into master



## 4.9合并冲突

合并分支时，master和dev两个分支在同一个文件的同一个位置有两套完全不同的修改，就会产生冲突。合并时弹出Conflict弹窗，给出三个选项：Accept Yours（使用你的代码）、Accept Theirs（使用别人的代码）、Merge（手动选择合并）。

若选择第三个选项：

- 选择第三个选项时，会有三分代码展示，分别是master、result、dev的代码
- 点击>>或者<<选择应用哪边的代码，选择错了可以Ctrl+Z撤回重选。



# 5 IDEA集成GitEE

集成GitHub和GitEE步骤大致相同，仅无需安装插件。

## 5.1登录GitEE

安装GitEE插件：Settings-Plugins-gitee插件

登录GitEE：Settings-VersionControl-GitEE



## 5.2推送到远程仓库

方式一：

点击master分支的push功能

起别名，将仓库地址粘贴到URL中，一定要粘贴Https的地址⚠️

点击push即可上传到远程仓库

方式二：

点击工具栏Git，点击GitEE，点击Share Priject on Gitee，点击share，则自动在登录的gitee中创建项目同名仓库

注意：推送到远程仓库之后就可以push了⚠️



## 5.3克隆远程库到本地

方式一：

点击工具栏Git，点击clone...，输入仓库URL，选择clone的位置及名称进行克隆。

方式二：

点击工具栏Git，点击clone...，点击gitee，选择仓库进行克隆。



## 5.4从远程库拉取新内容

右键，点击Git，选择分支，点击Pull





# 6 GitLab私服

## 6.1GitLab简介

GitLab 是一个用于仓库管理系统的开源项目，使用Git作为代码管理工具，并在此基础上搭建起来的Web服务。点击官网最下方的install可以查看安装说明，选择CentOS7查看安装文档

官网：https://about.gitlab.com/



## 6.2安装GitLab

（1）准备一台安装了CentOS7的虚拟机

- 保证能上网
- 关闭防火墙
- 内存最好4G以上

（2）将资料中的gitlab-ce-13.10.2-ce.0.el7.x86_64.rpm安装包上传到Linux的/opt/software目录下

（3）执行以下命令安装依赖的工具包

```shell
yum install -y curl policycoreutils-python openssh-server perl
```

（4）在gitlab的rpm包所在的目录执行以下命令安装gitlab

```shell
rpm -ivh gitlab-ce-13.10.2-ce.0.el7.x86_64.rpm
```

（5）执行以下命令初始化GitLab服务（需要等待好长时间）

```shell
gitlab-ctl reconfigure
```

（6）执行以下命令启动GitLab服务

```shell
gitlab-ctl start
```

（7）在浏览器地址栏输入虚拟机的ip地址访问GitLab

（8）在浏览器第一次访问需要修改root密码

（9）修改完密码使用root账户和新的密码进行登录

（10）登录成功，点击New project进入到创建仓库页面

（11）点击Create blank project创建仓库，创建仓库的界面与GitHub类似

（12） 如果要关闭GitLab服务执行以下命令

```shell
gitlab-ctl stop
```

（13）只执行上面的命令GitLab依赖的其他服务还在运行，想要彻底关闭所有相关服务需要执行以下命令

```shell
systemctl stop gitlab-runsvdir
```

（14）可以将以上服务的开机自启关闭（看情况操作）

```shell
systemctl disable gitlab-runsvdir
```

（15）将gitlab-runsvdir服务开机自启禁用掉之后，以后开启虚拟机直接执行gitlab-ctl start启动GitLab将失败，直接执行以下命令启动即可，不需要再执行gitlab-ctl start

```shell
systemctl start gitlab-runsvdir
```



## 6.3在Idea上安装GitLab插件

第一步：点击Settings-Plugins-在插件商店搜索GitLab，安装即可（注意：IDEA2020、2021不可与GitEE插件同时开启，会冲突⚠️）

第二步：配置GitLab服务器，点击Settings-VersionControl-GitLab开始配置：

GitLab UI Service Url配置虚拟机IP地址

```shell
39.106.35.112
```

Prefferred checkout method选择HTTPS协议

```shell
HTTPS
```

配置成功，保存配置



## 6.4推送本地库到GitLab

（1）在GitLab上创建远程库gitlab_test

（2）点击Clone，复制仓库的HTTP地址

（3）在Idea中定义GitLab远程地址的别名（点击分支的push，定义别名和URL）

需要修改仓库URL的域名为Linux服务器地址，例如：

```shell
http://gitlab.example.com/root/gitlab_test.git，
#修改仓库URL
http://39.106.35.112/root/gitlab_test.git，
```

（4）点击Push将Idea中的idea_git888项目推送到GitLab

（5）打开网页，输入GitLab的用户名和密码点击Log In登录查看

（6）推送成功





# 7 Git工作流

## 7.1简介

简单来说就是一个项目的成员们在工作中统一使用Git的工作方式。

GitFlow工作流通过为功能开发、发布准备和维护设立了独立的分支，让发布迭代过程更流畅。严格的分支模型也为大型项目提供了一些非常必要的结构。

比如出现BUG和正常开发分别使用不同的分支。



## 7.2分支的种类

**主干分支（master）**

- 主要负责管理正在运行的生产环境代码。永远保持与正在运行的生产环境完全一致。

**开发分支（develop）**

- 主要负责管理正在开发过程中的代码。一般情况下应该是最新的代码。 

**bug修理分支（hotfix）**

- 主要负责管理生产环境下出现的紧急修复的代码。 从主干分支分出，修理完毕并测试上线后，并回主干分支。并回后，视情况可以删除该分支。

**发布版本分支（release）**

- 较大的版本上线前，会从开发分支中分出发布版本分支，进行最后阶段的集成测试。该版本上线后，会合并到主干分支。生产环境运行一段时间较稳定后可以视情况删除。

**功能分支（feature）**

- 为了不影响较短周期的开发工作，一般会把中长期开发模块从开发分支中独立出来。 开发完成后会合并到开发分支。



## 7.3开发流程

- 正常开发环境下的分支：master分支 — develop分支(保证最新代码) — 多个feature分支(如feature_admin、feature_user等)⚠️
- 出现BUG情况下的分支：master(v1.0) — hotfix(创建分支修复BUG，然后合并到master分支生成新版本) — master(v1.1)⚠️
- 完成开发开启上线分支：develop分支(保证最新代码) — release(集成和测试) —master(v1.2)&develop更新(发现BUG开启BUG分支，修改后再并到主分支和开发分支)

总结：develop分支时刻保持最新代码，和master同步。出现bug修复后同时并到master和develop⚠️

