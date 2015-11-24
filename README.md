
## 実行方法

### テスト

## 設計の指針
オブジェクト指向を学ぶということで行列をベクトルの集合体として表現している。
Matrix内での計算をVectorに移譲できるため、Matrix内の計算を簡潔にできる。

add(A, B)

### エラーについて
ORを使って全ての場合に対して例外を出すのではなく
各例外に対して個別のエラーメッセージを用意するべき

## 
拡張性と汎用性を重視したため課題そのものには必要無いメソッドも実装している。
Javaはデフォルトでassertionを無効にしている。
実行時に -esa | -enablesystemassertions を指定してもなおassertキーワードが無視されたためAssertを自前で実装し使用している。


## 命名に関して
.NETの[名前に関するガイドライン](https://msdn.microsoft.com/ja-jp/library/ms229002.aspx)は非常に参考になる
[型のメンバーの名前](https://msdn.microsoft.com/ja-jp/library/ms229012.aspx)


### TODO
適切な例外を設定する
