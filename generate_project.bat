@echo off
chcp 65001 >nul
echo 开始生成项目文件...

if not exist "app\src\main\java\com\smartelderly\community\app" mkdir "app\src\main\java\com\smartelderly\community\app"
if not exist "app\src\main\java\com\smartelderly\community\ui\activity" mkdir "app\src\main\java\com\smartelderly\community\ui\activity"
if not exist "app\src\main\java\com\martelderly\community\ui\fragment" mkdir "app\src\main\java\com\martelderly\community\ui\fragment"
if not exist "app\src\main\java\com\smartelderly\community\ui\adapter" mkdir "app\src\main\java\com\smartelderly\community\ui\adapter"
if not exist "app\src\main\java\com\smartelderly\community\data\model" mkdir "app\src\main\java\com\smartelderly\community\data\model"
if not exist "app\src\main\java\com\smartelderly\community\data\network\interceptor" mkdir "app\src\main\java\com\martelderly\community\data\network\interceptor"
if not exist "app\src\main\java\com\smartelderly\community\data\network\request" mkdir "app\src\main\java\com\martelderly\community\data\network\request"
if not exist "app\src\main\java\com\smartelderly\community\data\repository" mkdir "app\src\main\java\com\smartelderly\community\data\repository"
if not exist "app\src\main\java\com\smartelderly\community\data\preference" mkdir "app\src\main\java\com\smartelderly\community\data\preference"
if not exist "app\src\main\java\com\martelderly\community\data\room\dao" mkdir "app\src\main\java\com\martelderly\community\data\room\dao"
if not exist "app\src\main\java\com\smartelderly\community\data\room\entity" mkdir "app\src\main\java\com\smartelderly\community\data\room\entity"
if not exist "app\src\main\java\com\smartelderly\community\viewmodel" mkdir "app\src\main\java\com\smartelderly\community\viewmodel"
if not exist "app\src\main\java\com\smartelderly\community\service" mkdir "app\src\main\java\com\smartelderly\community\service"
if not exist "app\src\main\java\com\smartelderly\community\receiver" mkdir "app\src\main\java\com\smartelderly\community\receiver"
if not exist "app\src\main\java\com\smartelderly\community\utils" mkdir "app\src\main\java\com\smartelderly\community\utils"
if not exist "app\src\main\java\com\smartelderly\community\widget" mkdir "app\src\main\java\com\smartelderly\community\widget"

if not exist "backend\src\main\java\com\smartelderly\community\controller" mkdir "backend\src\main\java\com\smartelderly\community\controller"
if not exist "backend\src\main\java\com\smartelderly\community\service\impl" mkdir "backend\src\main\java\com\smartelderly\community\service\impl"
if not exist "backend\src\main\java\com\smartelderly\community\service\interface" mkdir "backend\src\main\java\com\smartelderly\community\service\interface"
if not exist "backend\src\main\java\com\smartelderly\community\mapper" mkdir "backend\src\main\java\com\smartelderly\community\mapper"
if not exist "backend\src\main\java\com\smartelderly\community\entity" mkdir "backend\src\main\java\com\smartelderly\community\entity"
if not exist "backend\src\main\java\com\smartelderly\community\dto\request" mkdir "backend\src\main\java\com\smartelderly\community\dto\request"
if not exist "backend\src\main\java\com\smartelderly\community\dto\response" mkdir "backend\src\main\java\com\smartelderly\community\dto\response"
if not exist "backend\src\main\java\com\smartelderly\community\config" mkdir "backend\src\main\java\com\smartelderly\community\config"
if not exist "backend\src\main\java\com\smartelderly\community\common" mkdir "backend\src\main\java\com\smartelderly\community\common"
if not exist "backend\src\main\java\com\smartelderly\community\utils" mkdir "backend\src\main\java\com\smartelderly\community\utils"
if not exist "backend\src\main\java\com\smartelderly\community\security" mkdir "backend\src\main\java\com\smartelderly\community\security"

if not exist "app\src\main\res\values" mkdir "app\src\main\res\values"
if not exist "app\src\main\res\drawable" mkdir "app\src\main\res\drawable"
if not exist "app\src\main\res\layout" mkdir "app\src\main\res\layout"
if not exist "app\src\main\res\xml" mkdir "app\src\main\res\xml"
if not exist "app\src\main\res\mipmap-hdpi" mkdir "app\src\main\res\mipmap-hdpi"
if not exist "app\src\main\res\mipmap-mdpi" mkdir "app\src\main\res\mipmap-mdpi"
if not exist "app\src\main\res\mipmap-xhdpi" mkdir "app\src\main\res\mipmap-xhdpi"
if not exist "app\src\main\res\mipmap-xxhdpi" mkdir "app\src\main\res\mipmap-xxhdpi"

echo 目录结构创建完成!
echo 项目已准备好开发,请使用Android Studio打开项目进行详细开发。
pause
