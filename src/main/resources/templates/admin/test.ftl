<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>测试自定义宏</title>
</head>
<body>

   <#import "common/MyMacro.ftl" as  c >

    <hr>
     <@c.mydiv></@c.mydiv>
    <hr>
      <@c.mydiv></@c.mydiv>

     <hr>
     <@c.mydiv2  message="这是传递过来的字符串"></@c.mydiv2>

</body>
</html>