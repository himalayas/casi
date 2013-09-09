nginx搭建mp4、flv流媒体服务器

一、FLV视频发布方式简介

FLV视频有两总发布方式

1、  HTTP方式

这种方式要下载FLV视频文件到本地播放，一旦FLV视频文件下载完成，就不会消耗服务器的资源和带宽，但是拖动功能没有RTMP/RTMP流媒体方式强大，
很多视频网站都是用HTTP方式实现的，如：YouTube，土豆，酷6等

2、  RTMP/RTMP流媒体方式

这种方式不用下载FLV视频文件到本地，可以实时的播放flv文件，可以任意拖拽播放进度条，但是比较消耗服务器的资源，

二、使用nginx来搭建flv流媒体服务器

1、使用nginx来搭建flv流媒体服务器简介

nginx中的Flv Stream模块能实现flv流媒体的功能,而且支持flv视频进度条拖拽，另外nignx还可以作为方向代理服务器代理后端基于Flash Media Server或者Red5的RTMP/RTMP流媒体服务器


2、  下面我们就来搭建一个完整的nginx流媒体服务器

1）、Nginx服务器的安装

#安装zlib
wget http://downloads.sourceforge.net/project/libpng/zlib/1.2.8/zlib-1.2.8.tar.gz?r=http%3A%2F%2Fwww.zlib.net%2F&ts=1376457669&use_mirror=superb-dca3
tar xzvf zlib-1.2.3.tar.gz

cd zlib-1.2.3

./configure

make && make install

#安装pcre
wget http://sourceforge.net/projects/pcre/files/latest/download
tar zxvf pcre-7.9.tar.gz

cd pcre-7.9

./configure --prefix=/usr/local/pcre

make && make install

#添加mp4支持模块

wget http://h264.code-shop.com/download/nginx_mod_h264_streaming-2.2.7.tar.gz
tar -zxvf nginx_mod_h264_streaming-2.2.7.tar.gz

#安装nginx

groupadd www

useradd -g www www

tar xzvf nginx-0.8.34.tar.gz

cd nginx-0.8.34

./configure \
--prefix=/usr/local/nginx \
--add-module=../nginx_mod_h264_streaming-2.2.7 \
--with-http_ssl_module \
--with-pcre=../pcre-8.33 \
--with-zlib=../zlib-1.2.8 \
--with-http_flv_module \
--with-http_stub_status_module

make && make install

2）、安装yamdi

yadmi的作用是为flv文件添加关键帧，才能实现拖动播放

#下载yadmi

wget http://sourceforge.net/projects/yamdi/files/yamdi/1.4/yamdi-1.4.tar.gz/download

#安装yadmi

tar xzvf yamdi-1.4.tar.gz

cd yamdi-1.4

make && make install

使用方法：yamdi -i input.flv -o out.flv

给input.flv文件 添加关键帧，输出为out.flv文件

3）、配置nginx

vi /usr/local/nginx/conf/nginx.conf 添加以下内容（根据自身情况修改）：

user  www www;

worker_processes 30;

error_log  /usr/local/nginx/logs/error.log  crit;

pid /usr/local/nginx/logs/nginx.pid;

events {
        use epoll;
        worker_connections      65535;
        }
http {
        include       mime.types;
        default_type  application/octet-stream;
        log_format main  '$remote_addr - $remote_user [$time_local] '

                                                '"$request" $status $bytes_sent '

                                                '"$http_referer" "$http_user_agent" '

                                                '"$gzip_ratio"';

        keepalive_timeout  60;

        server_names_hash_bucket_size  128;

        client_header_buffer_size    32k;

        large_client_header_buffers  4 32k;

        access_log off;

        gzip on;

        gzip_min_length  1100;

        gzip_buffers     4 8k;

        gzip_types       text/plain;

        output_buffers   1 32k;

        postpone_output  1460;

        client_header_timeout  3m;

        client_body_timeout    3m;

        send_timeout           3m;

        sendfile                on;

        tcp_nopush              on;

        tcp_nodelay             on;

######################################################################

server {

        listen       80;

        server_name  192.168.1.105;

        root    /usr/local/nginx/html/flv_file/;

        limit_rate_after 5m;    ####在flv视频文件下载了5M以后开始限速
        limit_rate 512k;         ####速度限制为512K

        index   index.html;

        charset utf-8;

        location ~ \.flv {

            flv;

        }

        location ~ \.mp4$ {
                 mp4;
        }

        error_page   500 502 503 504  /50x.html;

        location = /50x.html {

            root   html;

        }

}

}

4）、基本上已经设置完毕，但是此时我们测试的时候还需要一个支持拖拽播放的flash播放器，开源的JW Player就可以实现这样的功能，我将编译的播放器上传上来，供大家下载：

       下载链接：http://blogimg.chinaunix.net/blog/upfile2/100607142612.rar

       下载播放器后，上传到上面设置的/usr/local/nginx/html/flv_file/目录下，闭关把flv视频文件也放到该目录下！

5）、启动nginx后测试：

       http://192.168.1.105/player.swf?type=http&file=test1.flv

说明： #我的ip是192.168.1.105

#player.swf是我的JW Player播放器

#http是表示居于http分发方式

#test1.flv是我的flv视频文件