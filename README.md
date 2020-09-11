我们经常会遇到需要解析命令行参数的场景。如果没有趁手的工具，我们可以自己写一个，自己想办法处理传给main函数的参数。 我们期望能把传递进来的字符串参数，转换成结构化数据，以便我们使用这些参数。

传递给程序的参数包含两个部分：
* flag: 是一个字符，在传入给程序时必须前置一个短横线，flag后可以有0个或1个value
* value: 跟在flag后的具体值

例如：”-p 8080”

我们需要解析上述这种参数，同时我们需要一个schema来指定期望接受到的参数格式，schema会指定flag和value的数量及类型。当程序将参数的字符串传递给程序后，程序会首先检查传入的参数是否符合schema的要求。

例如：
程序希望的传入参数如下：
-l true -p 8080 -d /usr/logs

那么对应的schema要求应为：
* 参数可以有三种flag: l, p, d
* l的value类型为boolean
* p的value类型为整数
* d的value类型为string

假如在参数中，schema支持的flag没有对应的value，那么value值为默认值。例如布尔值的默认值为false，数值的默认值为0，字符串的默认值为""。
假如在参数中，没有传入支持的flag，在使用该flag时也使用其默认值。
假如传入的参数不符合schema的规定，我们必须给出友好的错误提示以告知到底什么出了问题。


备注：
1.  整个字符串前后可以有空格，比如”    -l  true    ”
2. 参数之间不用空格分割视为非法，比如-p-d
3. 不允许输入重复flag
4. value不能以-开头，如果以-开头，视作另一个参数的开始
5. -与flag之间加空格报错, value中间有空格报错
6. flag与value之间，flag之间可以有多空格

## 代码结构

1. Args
```
Args(Schema schema, Lexer lexer)
ArgumentParser(schema)

=> List<Argument>
```
1.1 词法分析Lexer
```
String: "-l true -p 8080 -d /usr/logs"

=> List<String>: ["-l true","-p 8080","-d /usr/logs"]
```
1.2 语法分析ArgumentParser
```
List<String>: ["-l true","-p 8080","-d /usr/logs"]
其中 "-l true"

=> ["-l","true"]

=> Argument(flag, value)
```
1.3 Schema
```
String: "l:boolean,p:integer,d:string"

=> List<String>: ["l:boolean","p:integer","d:string"]

=> List<SchemaElement>: SchemaElement(String flag, Object type)

=> Schema: Set<SchemaElement>
```

2. 异常
- flag没有在Schema中定义
- 输入的字符串中flag重复

使用正则匹配可以忽略的异常：
- 词法分析阶段：
    - flag不是中线开始，"p 8080"
-  语法分析阶段：
    - flag是多个字符，"-pp 8080"
    - value中间有空格，"-p 80 80"
    - flag重复"-p 8080 -p true"
- schema的flag重复

词法分析中会涉及的异常：
- 字符串为空
- trim后的字符串不以"-"开始

语法分析中会涉及的异常：
- flag没有定义
- flag不是一个字符，是多个字符
- value中间有空格

schema定义的异常
- schema中有重复的flag

---------------------------------------------------
---------------------------------------------------

## 上下文：
- 上下文1：分割字符串参数为多个部分，每个部分是(-flag value)
    - 输入：字符串参数，形如"-l true -p 8080 -d /usr/logs"
    - 输出：分割为flag和value的字符串列表，形如["-l true", "-p 8080","-d /usr/logs"] 
- 上下文2：参照schema来将每个部分中的flag和value提取出来
    - 有flag没有value的需要设置为默认值
    - 没有flag没有value的需要设置为默认值
    - 输入：flag和value的字符串列表，形如["-l true", "-p 8080","-d /usr/logs"]
    - 输出：识别出的flag和value，形如[{flag: "l", value: true},{flag: "p", value: int},{flag: "d", value: "/usr/logs"}]
- 上下文3：Schema parser
    - 输入：string，形如"l:boolean,p:integer"   
    - 输出：List<SchemaElement>，形如[{flag: "l", type: Boolean},{flag: "p", type: Integer}]
- validation
    - 上下文1的验证
        - 字符串参数为空，报exception
        - 字符串参数过长，实际传输时需要考虑，这里暂不考虑
        - 字符串参数之间不用空格分割视为非法，比如-p-d，不分割
    - 上下文2的验证
        - 输入重复flag，报exception
        - -与flag之间加空格报错, value中间有空格报错
    - 合法性处理
        - 整个字符串前后可以有空格，比如”    -l  true    ”
        - value不能以-开头，如果以-开头，视作另一个参数的开始
        - flag与value之间，flag之间可以有多空格   
  
## 设计   
- 将字符串变为结构化数据 =》 操作结构化数据    
- 上下文1设计
    - Parser(string input) 
        - parse() => ["",""]
- 上下文2设计    
    - Schema([{flag, type}])
    - SchemaElement(flag, type)
    - Formatter(["",""]) => List<Argument>
    - Argument(flag, value)
    - Args(Schema, List<Argument>) => [{flag:value}]
        