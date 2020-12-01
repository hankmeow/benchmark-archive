

## 层级
最顶层的pom.xml用以放置基本类库继承和子模块聚合
第二层的pom.xml根据框架进行区分。如无框架、spring框架、vertx框架等。仅做同类库聚合。
第三层的pom.xml是实际的代码项目

均可使用IDEA的目录创建，注意三层创建时一定要选择父模块为指定的二层的artifactId，且三层的artifactId名字要和目录名相同，并加上二层artifactId的前缀。

