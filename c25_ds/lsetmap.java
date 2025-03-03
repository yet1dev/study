//========================================================
//                    LOGICAL SET MAP
//========================================================
import java.util.*; 
import java.util.Set;

public class lsetmap {
	public static void main(String[] args){

	}
}


public class LSetMap<T> { }

//========================================================
@SuppressWarnings("unchecked")
public abstract class LBox{
	protected LSetMap<?> repo;
	public <T> T repo(LSetMap<T> repo){ 
		this.repo = repo; return (T) this;
	}
}
//========================================================
//                      INTERFACES
//========================================================
@SuppressWarnings("unchecked")
public interface LSetStructure<T extends LBox> {
	Set<T> pick(int uaib, Set<T>... sets); 
	Set<T> load(String prop, Object val);
	void save(T obj, String prop, Object val);
	void drop(T obj, String prop, Object val);

	T          obj(int... idx);     // get object of bkp[idx]
	Set<T>     get(int... idx);     // get the set bkp[idx]
	LSetMap<T> set(int... idx);     // save bkp[idx] in all
	LSetMap<T> del(int... idx);     // delete bkp[idx] in all
	LSetMap<T> add(Object... args); // add object in all

	LSetMap<T> New(int n);       // add n void sets
	LSetMap<T> cur(int... idx);  // cur = bkp[idx]
	LSetMap<T> pop(int... idx);  // bkp.pop(idx)

	int qnt(int... idx);     // count elements in bkp[idx]
	String str(int... idx);  // return bkp[idx] as string
}
//========================================================
@SuppressWarnings("unchecked")
public interface LSetOperators<T extends LBox> {
	LSetMap<T> bkp(int... idx); // bkp[idx] = cur   << A
	LSetMap<T> inv(int... idx); // bkp[idx] = ~cur  << ~A
	LSetMap<T> all(int... idx); // bkp[idx] = all   << true
	LSetMap<T> rst(int... idx); // bkp[idx] = void  << false
	
	LSetMap<T> pin(String prop, Object val); // cur = filter  << B
	LSetMap<T> not(String prop, Object val); // cur = ~filter << ~B

	LSetMap<T> sel(String prop, Object val); // cur = cur & filter   << and
	LSetMap<T> mix(String prop, Object val); // cur = cur + filter   << or
	LSetMap<T> alt(String prop, Object val); // cur = cur \ filter   << xor
	LSetMap<T> cut(String prop, Object val); // cur = cur - filter   << dif
	LSetMap<T> lim(String prop, Object val); // cur = filter - cur   << dif

	LSetMap<T> sep(String prop, Object val); // cur = ~(cur & filter) << nand
	LSetMap<T> out(String prop, Object val); // cur = ~(cur + filter) << nor
	LSetMap<T> grp(String prop, Object val); // cur = ~(cur \ filter) << xnor
	LSetMap<T> ign(String prop, Object val); // cur = ~(cur - filter) << ndif
	LSetMap<T> esc(String prop, Object val); // cur = ~(filter - cur) << ndif
}


