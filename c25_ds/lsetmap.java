//========================================================
//                    LOGICAL SET MAP
//========================================================
import java.util.*; 
import java.util.Set; 

public class lsetmap {
	public static void main(String[] args){  
		
	}
}

public class LSetMap<T> {}

//========================================================
//                      INTERFACES
//========================================================
public interface LSetStructure { 
	<T> T          obj(int... idx);     // get object of bkp[idx]
	<T> Set<T>     get(int... idx);     // get the set bkp[idx]
	<T> LSetMap<T> set(int... idx);     // save bkp[idx] in all
	<T> LSetMap<T> del(int... idx);     // delete bkp[idx] in all
	<T> LSetMap<T> add(Object... args); // add object in all

	<T> LSetMap<T> New(int n);       // add n void sets
	<T> LSetMap<T> cur(int... idx);  // cur = bkp[idx]
	<T> LSetMap<T> pop(int... idx);  // bkp.pop(idx)

	int qnt(int... idx);     // count elements in bkp[idx]
	String str(int... idx);  // return bkp[idx] as string
}

//========================================================
@SuppressWarnings("unchecked")
public interface LSetOperators {
	<T> Set<T> pick(int uaib, Set<T>... sets); 

	<T> LSetMap<T> bkp(int... idx); // bkp[idx] = cur   << A
	<T> LSetMap<T> inv(int... idx); // bkp[idx] = ~cur  << ~A
	<T> LSetMap<T> all(int... idx); // bkp[idx] = all   << true
	<T> LSetMap<T> rst(int... idx); // bkp[idx] = void  << false
	
	<T> LSetMap<T> pin(String prop, Object val); // cur = filter  << B
	<T> LSetMap<T> not(String prop, Object val); // cur = ~filter << ~B

	<T> LSetMap<T> sel(String prop, Object val); // cur = cur & filter   << and
	<T> LSetMap<T> mix(String prop, Object val); // cur = cur + filter   << or
	<T> LSetMap<T> alt(String prop, Object val); // cur = cur \ filter   << xor
	<T> LSetMap<T> cut(String prop, Object val); // cur = cur - filter   << dif
	<T> LSetMap<T> lim(String prop, Object val); // cur = filter - cur   << dif

	<T> LSetMap<T> sep(String prop, Object val); // cur = ~(cur & filter) << nand
	<T> LSetMap<T> out(String prop, Object val); // cur = ~(cur + filter) << nor
	<T> LSetMap<T> grp(String prop, Object val); // cur = ~(cur \ filter) << xnor
	<T> LSetMap<T> ign(String prop, Object val); // cur = ~(cur - filter) << ndif
	<T> LSetMap<T> esc(String prop, Object val); // cur = ~(filter - cur) << ndif
}


