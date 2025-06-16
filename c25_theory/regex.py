#==============================================================
#                       REGEX LIBRARY
#==============================================================
def handleType(args, t, lenArgs="*", lenItem="*"):
  error = f"Expect {t.__name__}[{lenItem}], but receive"

  if not isinstance(args, list):
    raise TypeError(f"{error} {type(args).__name__}")
  if not all(isinstance(item, t) for item in args):
    raise TypeError(f"{error} some item not is {t}")
  if (lenArgs != "*") and len(args) != lenArgs:
    raise TypeError(f"{error} {len(args)} elements")
  if (lenItem != "*") and not all(len(item) == lenItem for item in args):
    raise TypeError(f"{error} a item with size different of {lenItem}")

  return args

#==============================================================
class Regex: pass

class RChar(Regex):
  def __init__(self, val1):
    self.val1 = handleType(val1, str, len(val1)==1)

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
class RConcat(Regex2): pass
class RRange(Regex0): pass

