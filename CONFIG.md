# 配置说明

## 首次运行项目前需要配置

1. 复制 `src/main/resources/application.yaml.example` 为 `application.yaml`
   ```bash
   cp src/main/resources/application.yaml.example src/main/resources/application.yaml
   ```

2. 编辑 `application.yaml`，填入以下真实配置信息：

### 数据库配置
- `spring.datasource.username`: 数据库用户名
- `spring.datasource.password`: 数据库密码

### 阿里云 OSS 配置
- `aliyun.oss.access-key-id`: 你的阿里云 AccessKey ID
- `aliyun.oss.access-key-secret`: 你的阿里云 AccessKey Secret
- `aliyun.oss.bucket-name`: OSS Bucket 名称

3. **重要**: `application.yaml` 文件包含敏感信息，已添加到 `.gitignore`，不会被提交到 Git。

## 安全提示

- ❌ 不要将 `application.yaml` 上传到公共仓库
- ✅ 使用 `.gitignore` 保护敏感配置文件
- ✅ 生产环境建议使用环境变量或配置中心管理敏感信息
