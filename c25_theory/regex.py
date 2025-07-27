#==============================================================
#                       REGEX LIBRARY
#==============================================================
from collections import defaultdict

class PrettyDefaultDict(defaultdict):
  def __repr__(self):
    lines = []
    for key, values in self.items():
       values_repr = ', '.join(str(v) for v in values)
       lines.append(f"  {repr(key)} → {{ {values_repr} }}")
    return "{\n" + '\n'.join(lines) + "\n}"

#==============================================================
class Type:
  def __init__(self, *types):
    self.types = types

  def get(self, val):
    lvl = 0
    buf = [val]
    types = list(self.types[::-1])

    while len(types) > 0:
      t = types.pop()
      if isinstance(t, int) and not all(t==len(x) for x in buf):
        raise TypeError(f"[LVL {lvl}] >> Some element not have {t} itens")
      if not isinstance(t, int) and not all(isinstance(x, t) for x in buf):
        raise TypeError(f"[LVL {lvl}] >> Receive a not {t}")
      if len(types) > 0 and not isinstance(types[-1], int):
        buf  = [x for A in buf for x in A]
        lvl += 1
    return val

#==============================================================
class Regex:
  def __repr__(self):
    return self.regex

class MVoid(str):    # epsilon/void need be a char
  def __new__(cls):
    return str.__new__(cls, 'ε')

  def __len__(self):
    return 1
#==============================================================
class StateNFA:
  def __init__(self):
    self.links = PrettyDefaultDict(set)

  def __repr__(self):
    return f'q{self.NFA.all.index(self)}' + '*'*(self is self.NFA.end)

  def setMachine(self, machine):
    self.NFA = Type(MachineNFA).get(machine)

  def link(self, char, state):
    char  = Type(str,1).get(char)
    state = Type(StateNFA).get(state)
    self.links[char].add(state)

#==============================================================
class MachineNFA:
  def __init__(self, fst, end):
    self.fst = Type(StateNFA).get(fst)
    self.end = Type(StateNFA).get(end)
    self.all = [fst, end]

  def load(self, states):
    filtered = [s for s in states if s not in (self.fst, self.end)]
    self.all = [self.fst] + filtered + [self.end]

    for state in self.all:
        state.setMachine(self)

  def __repr__(self):
    lines = []
    for state in self.all:
      trans = []
      for char, targets in state.links.items():
        for t in targets:
          trans.append(f'{char}/{t}')
      trans_str = ', '.join(trans) if trans else '∅'
      lines.append(f'  {state}: {trans_str}')
    return '\n'.join(lines)

#==============================================================
class RChar(Regex):
  def __init__(self, char):
    self.R1 = Type(str, 1).get(char)
    self.regex = f'{char}'

    NFA = MachineNFA(StateNFA(), StateNFA())
    NFA.fst.link(char, NFA.end)
    self.NFA = NFA

#==============================================================
class RStar(Regex):
  def __init__(self, R1):
    M1 = R1.NFA
    self.R1 = Type(Regex).get(R1)
    self.regex = f'({R1.regex})*'

    NFA = MachineNFA(StateNFA(), StateNFA())
    NFA.load(M1.all)

    NFA.fst.link(MVoid(), NFA.end)
    NFA.fst.link(MVoid(), M1.fst)
    M1.end.link(MVoid(), M1.fst)
    M1.end.link(MVoid(), NFA.end)
    self.NFA = NFA

#==============================================================
class ROr(Regex):
  def __init__(self, R1, R2):
    M1, M2 = R1.NFA, R2.NFA
    self.R1 = Type(Regex).get(R1)
    self.R2 = Type(Regex).get(R2)
    self.regex = f'({R1.regex}|{R2.regex})'

    NFA = MachineNFA(StateNFA(), StateNFA())
    NFA.load(M1.all + M2.all)

    NFA.fst.link(MVoid(), M1.fst)
    NFA.fst.link(MVoid(), M2.fst)
    M1.end.link(MVoid(), NFA.end)
    M2.end.link(MVoid(), NFA.end)
    self.NFA = NFA

class RConcat(Regex):
  def __init__(self, R1, R2):
    M1, M2 = R1.NFA, R2.NFA
    self.R1 = R1
    self.R2 = R2
    self.regex = f'{R1.regex}{R2.regex}'

    self.NFA = MachineNFA(M1.fst, M2.end)
    self.NFA.load(M1.all + M2.all)

    M1.end.link(MVoid(), M2.fst)

#==============================================================
class RRange(Regex):
  def __init__(self, R1, R2):
    self.R1 = Type(str, 1).get(R1)
    self.R2 = Type(str, 1).get(R2)
    self.regex = f'[{R1}-{R2}]'

