基于 [Timber][1]与[Logan][2]的安装日志库

timber为源码引入，并对timber源码做很小的改动，改动点:
1. 移除 `class Timber`及其构造函数的 `actual` 标记
2. 移除 `class Tree`及其成员函数的 `actual` 标记
3. 移除 `companion object Forest`及其成员函数的 `actual` 标记
4. 更改 `DebugTree`中fqcnIgnore的访问控制为 `internal open`

目前源码所使用的timber版本为5.0.1


[1]: https://github.com/JakeWharton/timber
[2]: https://github.com/Meituan-Dianping/Logan