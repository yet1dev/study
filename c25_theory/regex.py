#==============================================================
#                       REGEX LIBRARY
#==============================================================
from collections import defaultdict

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
class Regex: pass

class MVoid(str):    # epsilon/void need be a char
  def __new__(cls):
    return str.__new__(cls, 'ε')

  def __len__(self):
    return 1
#==============================================================
class StateNFA:
  def __init__(self):
    self.links = defaultdict(set)

  def __repr__(self):
    return f'<{id(self):x}>'

  def link(self, char, state):
    char  = Type(str,1).get(char)
    state = Type(StateNFA).get(state)
    self.links[char].add(state)

#==============================================================
#==============================================================
class RChar(Regex):
  def __init__(self, args*):
    self.args = handleType(args, str, 1, 1)

class RStar(Regex):
  def __init__(self, args*):
    self.args = handleType(args, Regex, 1)

#==============================================================
class ROr(Regex):
  def __init__(self, args*):
    self.args = handleType(args, Regex, 2)

class RAnd(Regex):
  def __init__(self, args*):
    self.args = handleType(args, Regex, 2)

class RConcat(Regex):
  def __init__(self, args*):
    self.args = handleType(args, Regex, 2)

class RRange(Regex):
  def __init__(self, args*):
    self.args = handleType(args, Regex, 2)

