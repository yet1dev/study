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

