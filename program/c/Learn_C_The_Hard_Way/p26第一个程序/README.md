## 功能
devpkg是一个简单的C程序，可以用于安装其它软件。

## 目标
devpkg -S
在电脑上安装新的软件。
devpkg -I
从URL安装软件。
devpkg -L
列出安装的所有软件。
devpkg -F
为手动构建抓取源代码。
devpkg -B
构建所抓取的源码代码并且安装它，即使它已经安装了。

devpkg能够接受几乎任何URL，判断项目的类型，下载，安装，以及注册已经安装的软件。

## 设计
1. 外部命令
curl、git、tar
2. 文件数据库
文件数据库 /usr/local/.devpkg/db 跟踪已经安装的软件
3. /usr/local
项目在 /usr/local 中
4. configure; make; make install
假设大多数软件可以通过 configure; make; make install 来安装。
如果软件不能通过这种方式安装，要么提供某种方式来修改命令，要么devpkg就可以无视它。
5. 用户可以root
这会使我们的程序像当初设想的一样简单，并且对于它的功能来说已经足够了。

## Apache可移植运行时（APR）
http://apr.apache.org/