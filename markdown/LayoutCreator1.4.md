## 功能

可以让你在Activity/Fragment中自动生成findViewById等布局相关初始化代码<br/>
或者在Adapter中自动生成ViewHolder代码

## 安装

方式一. 下载项目中的jar包,通过本地disk方式导入<br/>
方式二. 在Android Studio的插件中心搜索LayoutCreator下载安装

## 用法

新建好Activity后自行编写onCreate并setContentView设置对应布局<br/>
选中layout布局,快捷键alt+Insert,然后选择LayoutCreator或者选中布局后在菜单栏中的Code中选择LayoutCreator<br/>
插件会自动遍历布局列出所有带id的控件,你可以在弹出的对话框中选择需要自动生成的控件<br/>
弹出的对话框中还可以勾选是否生成ViewHolder<br/>
选择好后Confirm确认即可
代码生成规则
自动遍历目标布局中所有带id的文件, 无id的不会识别处理
控件生成的变量名默认为id名称, 可以在弹出确认框右侧的名称输入栏中自行修改
所有的Button或者带clickable=true的控件, 都会自动在代码中生成setOnClickListener相关代码
所有EditText控件, 都会在代码中生成非空判断代码, 如果为空会提示EditText的hint内容, 如果hint为空则提示xxx字符串不能为空字样, 最后会把所有输入框的验证合并到一个submit方法中
会自动识别布局中的include标签, 并读取对应布局中的控件

文／boredream（简书作者）
原文链接：http://www.jianshu.com/p/3993aac140c5
著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。