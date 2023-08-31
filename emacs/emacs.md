# Emacs

## Emacs基本操作速记

+ C，表示的是CTRL键
+ M，代表的是ALT键

光标的移动：

+ C-n：下一行(Next line)
+ C-p：上一行(Previous line)
+ C-f：向前移动一个字符(Forward)
+ C-b：向后退一个字符(Backward)
+ C-k：从光标位置到末尾删掉(Kill)
+ C-a：回到行首(a是字母的第一个)
+ C-e：回到行尾(End of line)
+ M-<：回到编辑区域最开始的位置(还需要按住shift)
+ M->：回到编辑区域最后的位置(还需要按住shift)
+ C-v：向下翻一屏
+ M-v：向上翻一屏

## 配置文件初体验

我们现在自己的home目录下新建.emacs文件，然后通过语句`emacs .emacs`通过emacs打开配置文件，之后使用快捷键`Ctrl+x`之后再按1,只保留当前窗口。

我们在`emacs`中写入：

~~~lisp
(menu-bar-mode -1)
(tool-bar-mode -1)
(scroll-bar-mode -1)
~~~

分别关闭菜单栏，工具栏和滚动条。之后`C+x`后`C+s`保存，`C+x`后`C+c`退出emacs后重新启动。

## Emacs配置文件位置

Emacs配置文件的位置，会按照以下顺序查找：

+ `~/.emacs`
+ `~/.emacs.d`
+ `~/.config/emacs/init.el`

## 第一行配置代码

`(setq inhibit-startup-screen t)`可以关掉启动界面。

`C+x`之后`C+f`可以寻找文件。

[Emacs快捷键](https://blog.csdn.net/qq_34159047/article/details/122400851)。

## 使用use-package管理拓展

+ 什么是use-package
  + 简单理解，是一个宏
  + 用更简单统一的方式去管理插件
+ 怎么用

~~~lisp
;; 最简单的格式
(use-package restart-emacs)
~~~