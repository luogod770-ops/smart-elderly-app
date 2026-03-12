#!/bin/bash
# 项目文件生成脚本
# 用途: 批量生成项目所需的Kotlin和Java文件

echo "开始生成项目文件..."

# 创建目录结构
mkdir -p app/src/main/java/com/smartelderly/community/{app,ui/{activity,fragment,adapter},data/{model,network/{interceptor,request},repository,preference,room/{dao,entity}},viewmodel,service,receiver,utils,widget}
mkdir -p app/src/main/res/{values,drawable,layout,mipmap-{hdpi,mdpi,xhdpi,xxhdpi,xxxhdpi},xml}
mkdir -p backend/src/main/{java/com/smartelderly/community/{controller,service/{impl,interface},mapper,entity,dto/{request,response},config,common,utils,security},resources}

echo "目录结构创建完成!"
echo "项目已准备好开发,请使用Android Studio打开项目进行详细开发。"
