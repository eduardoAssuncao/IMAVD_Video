# ReconhecimentoFacial
-Projeto de vídeo para a cadeira de IMAVD - ISEP

## Como executar:
-Para o funcionamento da funcionalidade, é necessário clonar o repositório
e ter os seguintes expecificações:
    -Java 1.8 ou maior;
    -Netbeans 8.2 ou maior.

-Bibliotecas JAR reponsável pelo reconheimento disponíveis em https://github.com/bytedeco/javacv/releases:
  -Faça o download da versão mais recente (javacv-platform-1.5.9-bin.zip)
    -FFMPEG-plataform;
    -FFMPEG-Windows-x86_64;
    -FFMPEG;
    -JavaCPP;
    -JavaCV-plataform;
    -JavaCV;
    -OpenCV-plataform;
    -OpenCV-Windows-x86_64;
    -OpenCV.

-Biblioteca JAR do FreeTTS disponível em https://sourceforge.net/projects/freetts/files/:
    -cmu_time_awb;
    -cmu_us_kal;
    -cmudict04;
    -cmulex;
    -cmutimelex;
    -en_us;
    -freetts;
    -freetts-jsapi10;
    -mbrola.

-Biblioteca JAR do mysqlconnector disponível em https://jar-download.com/artifacts/mysql/mysql-connector-java/5.1.47/source-code
    -mysql-connector-java-5.1.47.

-Além de ter todas as bibliotecas no projeto, é necessário ter o WampServer (https://sourceforge.net/projects/wampserver/)
e uma base de dados criada no phpMyAdmin com o nome facial_recognition e uma entidade chamada person que ira ter id, dob, first_name, last_name e office (informação da pessoa).

-Por fim, é necessário ter o arquivo haarcascade_frontalcatface.xml no caminho que é definido na classe capture e recognizer. Nesse caminho
que será colocado as capturas realizados na aplicação. o arquivo haarcascade_frontalcatface.xml por ser encontrado em https://github.com/opencv/opencv/tree/master/data/haarcascades