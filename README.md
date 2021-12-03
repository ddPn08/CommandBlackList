# CommandBlackList
プレイヤーがコマンド実行時に処理を行います。  

## usage  
config.ymlのテンプレート
```yaml
# params
# 
# commands : アクションを実行するコマンドのリスト
#   command : 対象のコマンド。ここに入力された値から始まるコマンドが実行された場合、指定されたアクションが実行されます。
#   actions : 実行するアクション。actionsで定義したアクションの名前を指定してください。


commands:
- command: /ban
  actions: 
  - Kick_Sender



# params
#
# actions : コマンドが実行されたときに実行するアクション
#   type : アクションの種類。 いまのところ command のみ
#     [command] 指定されたコマンドを実行します
#   data : アクションのデータ
#     type がコマンドの場合、実行するコマンド

actions:
- name: Kick_Sender
  type: command
  data: /tell ${sender} そのこまんどつかっちゃいかんで
```

### Command
|||
|-|-|
|/commandblacklist reload|config.ymlをリロードします。|

### Permission
|||
|-|-|
|CommandBlackList.ignore|この権限を持つプレイヤーには上記アクションが実行されません。|
