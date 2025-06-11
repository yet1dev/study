#==============================================================
#                       REGEX LIBRARY
#==============================================================
def istype(expr, t, limit=True):
  if isinstance(expr, t) and limit:
    return expr
  raise TypeError(f"Expect {t.__name__}, but receive {type(expr).__name__}")

#==============================================================
class Regex: pass

class RChar(Regex):
  def __init__(self, val1):
    self.val1 = istype(val1, str, len(val1)==1)

class Regex1(Regex):
  def __init__(self, val1):
    self.val1 = istype(val1, Regex)

class Regex2(Regex):
  def __init__(self, val1, val2):
    self.val1 = istype(val1, Regex)
    self.val2 = istype(val2, Regex)

#==============================================================
#                       LOGICAL  OPERATORS
#==============================================================
class RStar(Regex1): pass

class ROr(Regex2): pass
class RAnd(Regex2): pass
class RConcat(Regex2): pass # 

