
## 実行方法
### ガウス消去法
```
make
java matrix.TestLinearAlgebra
```

makeがインストールされていない場合

```
javac matrix/ArrayUtils.java matrix/Assert.java matrix/LinearAlgebra.java matrix/Math.java matrix/Matrix.java matrix/TestArrayUtils.java matrix/TestLinearAlgebra.java matrix/TestMatrix.java matrix/TestVector.java matrix/Vector.java
java matrix.TestLinearAlgebra
```

### 各クラスのテスト

```
make test
```

### テストについて
学校の環境で実行することを想定しているため、テストはJUnitを使わずに書いている。

### TODO
* JavaDocで書き直す
* JUnitテスト用ブランチを作成する
