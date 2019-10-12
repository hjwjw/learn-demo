

本示例为WebService的服务端.
演示普通 Java程序发布 WebService
分别有one,two,three三个示例。

---

- one: 最简的WebService服务端发布
- two: 使用到了各注解，说明了各注解使用方式
- three: 面向接口的WebService发布 
    > 使用接口方式的场景：cxf生成service的时候，service不能有继承关系，所以需要封装一层.