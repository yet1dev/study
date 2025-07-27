# Welcome to Computer Theory Archive
This is my Archive of Computer Theory UFRPE Class. The first work
is a basic library to convert regex expressions in NFA, DFA and
test if a chain is accept by a especific regex expression.

## Regex-lib
**Download:** [link](https://yet1dev.com/study/c25_theory/regex.py)
```
>>> from regex import *
>>> RChar('a').NFA
>>> RStar(RChar('a'), RChar('b')).NFA
>>> ROr(RChar('a'), RChar('b')).regex
```
#### Work Progress
- [x] **Represent:** ROr
- [x] **Represent:** RStar
- [x] **Represent:** RChar
- [x] **Represent:** RConcat
- [x] **Represent:** RRange
- [x] **Convert to NFA:** ROr
- [x] **Convert to NFA:** RStar
- [x] **Convert to NFA:** RChar
- [x] **Convert to NFA:** RConcat
- [ ] **Convert to NFA:** RRange
- [ ] **Matrix DFA Convertion**
- [ ] **Test if String is accepted**
