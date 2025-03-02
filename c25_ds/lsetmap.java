//============================================
//             LOGICAL SET MAP
//============================================
import java.util.*; 
import java.util.Set; 

public class lsetmap {
	public static void main(String[] args){  
		
	}
}

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

public class LSetMap<T> {}

