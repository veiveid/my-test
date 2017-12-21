my-test
====
spring+springmvc+mybatis

部署说明：
nginx(1)+tomcat(n)+mysql(1)
1、静态资源交由nginx管理 在nginx/html目录下新建目录my-test-web,再讲项目里的static 文件拷贝到这个目录下。

2、nginx配置片段：
...
#gzip  on;

	upstream vivi.com {
		server 127.0.0.1:8040;
		server 127.0.0.1:8050;
	}

    server {
        listen       80;
        server_name  localhost;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
			proxy_pass http://vivi.com;
            root   html;
            index  index.html index.htm;
			proxy_connect_timeout       1;
			proxy_read_timeout          1;
			proxy_send_timeout          1;
        }

		location ~ .*\.(html|htm|ico|png|jpg|jpeg|js|css|bmp|gif)$ {
            root html;
        }

      ...
3、讲war包分别放到tomcat webapps下
4、启动tomcat  启动Nginx
