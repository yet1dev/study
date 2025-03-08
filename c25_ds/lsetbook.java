//========================================================
//                 LOGICAL SET BOOK
//========================================================
import java.util.*; // Set, Map, List, Arrays, HashSet, HashMap, Iterator, ArrayList 
import java.lang.reflect.*;  // Field, Constructor
import java.util.stream.*;   // Stream, Collectors
import java.util.function.*; // Function, Consumer, Predicate, BinaryOperator

//========================================================
//            LBOX - BASE FOR INTERNAL ELEMENTS
//========================================================
@SuppressWarnings("unchecked")
public abstract class LBox<T> {
	protected LSetBook<T> repo;
	protected String props;
	protected String getters;
	protected String setters;
	protected static LSetBook<?> reporef;
//========================================================	
	public static <T> void repo(LSetBook<T> repo){ reporef = repo; }

	public <G> G get(String prop){
		return (getters.contains(prop)) ? localGet(prop) : null;
	}
	public <G> void set(String prop, G val){
		if (setters.contains(prop)) { localSet(prop, val); } 
	}
	public void rstIndex() {
		if (this.repo!=null){
			Arrays.stream(props.split(" "))
				.forEach(prop -> ((LSetMap<T>) repo).drop( (T) this, prop, localGet(prop) ) );
		}
	}
//========================================================	
	protected <G> G localGet(String prop) {
    Safe code = new Safe(() -> field(prop).get(this));
    return code.except("A propriedade nao existe: " + prop);
	}

	protected <G> void localSet(String prop, G val) {
		repo = (repo!=null) ? repo : (LSetBook<T>) reporef;

		if (this.repo!=null){
			G old = localGet(prop);
			((LSetMap<T>) repo).drop((T) this, prop, old);
			((LSetMap<T>) repo).save((T) this, prop, (G) val);
		}
		Safe code = new Safe(() -> field(prop).set(this, val));
		code.except("Propriedade nao existe: " + prop);
	}

	protected Field field(String prop) {
		Safe code = new Safe(() -> {
			Field campo = this.getClass().getDeclaredField(prop);
			campo.setAccessible(true);
			return campo;
		});
		return code.except("A propriedade nao existe: " + prop);
	}
}
//========================================================
//              INTERFACE OF LSETBOOK
//========================================================
@SuppressWarnings("unchecked")
public interface LSetBook<T> {
	T               obj(int... pag);         // get object of book[pag]
	Set<T>          get(int... pag);         // get the set book[pag]
	LSetBook<T>     del(int... pag);         // delete book[pag] in all
	LSetBook<T>     add(Object... args);     // add object in all
	<G> LSetBook<T> set(String prop, G val); // delete book[pag] in all

	LSetBook<T> New(int n);       // add n void sets
	LSetBook<T> rip(int n);       // book.pop(pag)
	LSetBook<T> pag(int... pag);  // cur = book[pag]

	int qnt(int... pag);     // count elements in book[pag]
	int sum(String prop);    // return sum prop in book[0]
//========================================================
	LSetBook<T> filter(Predicate<T> lambda);
	LSetBook<T> forEach(Consumer<T> lambda);
	<R> Set<R>  map(Function<T,R> lambda);
	T           reduce(T start, BinaryOperator<T> lambda);
//========================================================
	LSetBook<T>  to(int... pag); // book[pag] = cur   << A
	LSetBook<T> inv(int... pag); // book[pag] = ~cur  << ~A
	LSetBook<T> all(int... pag); // book[pag] = all   << true
	LSetBook<T> rst(int... pag); // book[pag] = void  << false
 
	<G> LSetBook<T> pin(String prop, G val); // cur = filter  << B
	<G> LSetBook<T> not(String prop, G val); // cur = ~filter << ~B

	<G> LSetBook<T> sel(String prop, G val); // cur = cur & filter   << and
	<G> LSetBook<T> mix(String prop, G val); // cur = cur + filter   << or
	<G> LSetBook<T> alt(String prop, G val); // cur = cur \ filter   << xor
	<G> LSetBook<T> cut(String prop, G val); // cur = cur - filter   << dif
	<G> LSetBook<T> lim(String prop, G val); // cur = filter - cur   << dif

	<G> LSetBook<T> sep(String prop, G val); // cur = ~(cur & filter) << nand
	<G> LSetBook<T> out(String prop, G val); // cur = ~(cur + filter) << nor
	<G> LSetBook<T> eqv(String prop, G val); // cur = ~(cur \ filter) << xnor
	<G> LSetBook<T> ign(String prop, G val); // cur = ~(cur - filter) << ndif
	<G> LSetBook<T> esc(String prop, G val); // cur = ~(filter - cur) << ndif
}
//========================================================
//             SAFE CLASS - ERROR HANDLING
//========================================================
public class Safe {
	private Object code;
	private String mensage;
	private boolean test;

	public void test(boolean test, String msg){
		this.test = test;
		this.mensage = msg;
	}

	public <T> Safe(TReturn<T> lambda) { 
		test(true, "");
		code = lambda; 
	}

	public <T> Safe(VoidReturn lambda) {
		test(true, "");
		code = (TReturn<T>) () -> { lambda.run(); return null; };
	}

	@SuppressWarnings("unchecked")
	public <T> T except(String msg) {
		if (this.test){
			try { return ((TReturn<T>) code).run(); } 
			catch (Exception e) { System.out.printf("[Error] %s %n", msg); System.exit(1); return null; } 
		} 
		else { System.out.printf("[Error] %s %n", mensage); System.exit(1); return null; }
	}
//==========================================
	@FunctionalInterface
	interface TReturn<T> { T run() throws Exception; }

	@FunctionalInterface
	interface VoidReturn { void run() throws Exception; }
}
