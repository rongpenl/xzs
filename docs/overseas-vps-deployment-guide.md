# 🌍 海外VPS + 商用数据库部署指南

## 📋 部署方案概述

### 架构设计
```
用户访问 → 域名 → 海外VPS (应用服务器) → 商用PostgreSQL数据库
```

### 核心优势
- **数据安全**: 商用数据库提供专业的数据保护
- **成本控制**: VPS只运行应用，降低资源需求
- **全球访问**: 海外VPS提供更好的国际访问体验
- **专业运维**: 数据库由专业服务商维护

## 💰 成本优化方案

### 总体预算: $20-40/月

#### VPS成本 ($5-15/月)
| 提供商 | 配置 | 价格 | 推荐度 |
|--------|------|------|--------|
| Vultr | 1GB内存, 25GB SSD | $6/月 | ⭐⭐⭐⭐⭐ |
| DigitalOcean | 1GB内存, 25GB SSD | $6/月 | ⭐⭐⭐⭐⭐ |
| Linode | 1GB内存, 25GB SSD | $5/月 | ⭐⭐⭐⭐ |
| AWS Lightsail | 1GB内存, 40GB SSD | $10/月 | ⭐⭐⭐ |

#### 数据库成本 ($10-25/月)
| 提供商 | 配置 | 价格 | 特点 |
|--------|------|------|------|
| Supabase | 512MB内存, 1GB存储 | $0-25/月 | 免费起步, 开发友好 |
| Aiven | 1GB内存, 10GB存储 | $19/月 | 专业可靠 |
| DigitalOcean | 1GB内存, 10GB存储 | $15/月 | 与VPS同平台 |
| ElephantSQL | 20连接, 20MB存储 | $9/月 | 简单易用 |

## 🛠️ 部署步骤详解

### 阶段1: VPS选购和配置

#### 1.1 选择VPS提供商
**推荐: Vultr 或 DigitalOcean**

**Vultr配置示例**:
- **Location**: Tokyo, Singapore, or Frankfurt (选择离用户近的)
- **Server Type**: Cloud Compute
- **CPU**: 1 vCPU
- **Memory**: 1GB
- **Storage**: 25GB SSD
- **Bandwidth**: 1TB
- **Cost**: $6/month

#### 1.2 系统初始化
```bash
# 更新系统
sudo apt update && sudo apt upgrade -y

# 安装必要软件
sudo apt install -y curl wget git vim

# 安装Java 8
sudo apt install -y openjdk-8-jdk

# 验证Java安装
java -version
```

#### 1.3 安全配置
```bash
# 配置防火墙
sudo ufw enable
sudo ufw allow 22    # SSH
sudo ufw allow 80    # HTTP
sudo ufw allow 443   # HTTPS

# 创建专用用户
sudo adduser xzsuser
sudo usermod -aG sudo xzsuser
```

### 阶段2: 商用数据库设置

#### 2.1 选择数据库提供商
**推荐: Supabase (免费起步)**

**Supabase配置步骤**:
1. 访问 https://supabase.com
2. 注册账户并创建新项目
3. 选择免费计划
4. 记录连接信息:
   - Host
   - Port
   - Database name
   - Username
   - Password

#### 2.2 数据库初始化
使用Supabase的SQL编辑器执行初始化脚本：

```sql
-- 创建数据库 (Supabase会自动创建)
-- 导入项目中的SQL文件
-- 文件位置: release/xzs-postgresql.sql
```

#### 2.3 测试数据库连接
```bash
# 在VPS上测试连接
psql "postgresql://username:password@host:port/database"
```

### 阶段3: 应用部署

#### 3.1 准备应用
```bash
# 在VPS上克隆项目
git clone https://github.com/your-repo/xzs.git
cd xzs/source/xzs

# 修改数据库配置
vim src/main/resources/application-prod.yml
```

#### 3.2 配置数据库连接
**文件**: `src/main/resources/application-prod.yml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://your-supabase-host:5432/your-database
    username: your-username
    password: your-password
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        show_sql: false
        format_sql: false

# 其他生产环境配置
server:
  port: 8000
  servlet:
    context-path: /
```

#### 3.3 构建和部署
```bash
# 构建应用
mvn clean package -DskipTests

# 创建启动脚本
cat > start-xzs.sh << 'EOF'
#!/bin/bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

cd /home/xzsuser/xzs/source/xzs
java -jar target/xzs-3.9.0.jar --spring.profiles.active=prod
EOF

chmod +x start-xzs.sh

# 使用systemd管理服务
sudo cat > /etc/systemd/system/xzs.service << 'EOF'
[Unit]
Description=XZS Exam System
After=network.target

[Service]
Type=simple
User=xzsuser
WorkingDirectory=/home/xzsuser/xzs/source/xzs
ExecStart=/home/xzsuser/xzs/source/xzs/start-xzs.sh
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
EOF

# 启动服务
sudo systemctl daemon-reload
sudo systemctl enable xzs
sudo systemctl start xzs
```

### 阶段4: 域名和SSL配置

#### 4.1 域名绑定
1. 在域名注册商处添加A记录:
   - Type: A
   - Name: @ (或子域名如 exam)
   - Value: 你的VPS IP地址

2. 等待DNS传播 (通常1-24小时)

#### 4.2 SSL证书配置
使用Certbot获取免费SSL证书:

```bash
# 安装Certbot
sudo apt install -y certbot python3-certbot-nginx

# 获取证书 (如果使用Nginx)
sudo certbot --nginx -d your-domain.com

# 或者使用 standalone 模式
sudo certbot certonly --standalone -d your-domain.com
```

#### 4.3 Nginx反向代理配置
```bash
# 安装Nginx
sudo apt install -y nginx

# 创建Nginx配置
sudo cat > /etc/nginx/sites-available/xzs << 'EOF'
server {
    listen 80;
    server_name your-domain.com;
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    server_name your-domain.com;

    ssl_certificate /etc/letsencrypt/live/your-domain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/your-domain.com/privkey.pem;

    location / {
        proxy_pass http://localhost:8000;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
EOF

# 启用配置
sudo ln -s /etc/nginx/sites-available/xzs /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```

## 🔧 监控和维护

### 基础监控
```bash
# 安装基础监控工具
sudo apt install -y htop iotop nethogs

# 查看应用日志
sudo journalctl -u xzs -f

# 查看系统资源
htop
df -h
```

### 自动备份
**数据库备份** (Supabase提供自动备份)
- 每日自动备份
- 可手动导出数据

**应用备份**:
```bash
# 创建备份脚本
cat > backup-xzs.sh << 'EOF'
#!/bin/bash
BACKUP_DIR="/home/xzsuser/backups"
DATE=$(date +%Y%m%d_%H%M%S)

# 备份应用配置
tar -czf $BACKUP_DIR/xzs_config_$DATE.tar.gz /home/xzsuser/xzs/source/xzs/src/main/resources/

# 保留最近7天的备份
find $BACKUP_DIR -name "*.tar.gz" -mtime +7 -delete
EOF

# 添加到cron
(crontab -l ; echo "0 2 * * * /home/xzsuser/backup-xzs.sh") | crontab -
```

## 🚨 故障排除

### 常见问题

#### 1. 应用无法启动
```bash
# 检查服务状态
sudo systemctl status xzs

# 查看详细日志
sudo journalctl -u xzs -n 50

# 检查端口占用
sudo netstat -tlnp | grep 8000
```

#### 2. 数据库连接失败
```bash
# 测试数据库连接
psql "postgresql://username:password@host:5432/database"

# 检查网络连通性
telnet your-supabase-host 5432
```

#### 3. 域名无法访问
```bash
# 检查DNS解析
dig your-domain.com

# 检查Nginx配置
sudo nginx -t

# 检查防火墙
sudo ufw status
```

## 📈 性能优化建议

### VPS优化
```bash
# 优化Java内存设置
# 修改启动脚本中的Java参数
java -Xms256m -Xmx512m -jar target/xzs-3.9.0.jar

# 配置swap空间 (如果内存不足)
sudo fallocate -l 1G /swapfile
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile
```

### 数据库优化
- 定期清理日志表
- 监控慢查询
- 使用数据库提供的性能分析工具

## 💡 扩展方案

### 当用户量增长时
1. **升级VPS**: 2GB内存 → $12/月
2. **升级数据库**: Supabase Pro计划 → $25/月
3. **添加CDN**: Cloudflare免费CDN
4. **负载均衡**: 添加第二个VPS实例

### 高可用方案
- 使用多个VPS实例
- 数据库读写分离
- 使用负载均衡器

## 🎯 部署检查清单

### 部署前检查
- [ ] VPS已购买并配置
- [ ] 商用数据库已创建
- [ ] 域名已购买并配置DNS
- [ ] 应用代码已准备好

### 部署中检查
- [ ] 数据库连接测试通过
- [ ] 应用构建成功
- [ ] 服务启动正常
- [ ] 域名解析正确
- [ ] SSL证书有效

### 部署后检查
- [ ] 网站可以正常访问
- [ ] 管理员和用户登录正常
- [ ] 试卷创建和答题功能正常
- [ ] 监控告警配置完成

## 🔗 相关资源

### 官方文档
- [Vultr文档](https://www.vultr.com/docs/)
- [DigitalOcean文档](https://docs.digitalocean.com/)
- [Supabase文档](https://supabase.com/docs)
- [Nginx文档](https://nginx.org/en/docs/)

### 工具和脚本
- 项目中的部署脚本
- 数据库初始化SQL
- 监控配置模板

---

**部署成功标志**: 通过域名可以正常访问学之思考试系统，所有功能正常运行，数据安全存储在商用数据库中。

按照这个指南，您可以在$20-40/月的预算内，部署一个稳定可靠的海外考试系统。