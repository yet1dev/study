//========================================================
//                 LOGICAL SET BOOK
//========================================================
import java.util.*; // Set, Map, List, Arrays, HashSet, HashMap, Iterator, ArrayList 
import java.lang.reflect.*;  // Field, Constructor
import java.util.stream.*;   // Stream, Collectors
import java.util.function.*; // Function, Consumer, Predicate, BinaryOperator
//========================================================
//             LSETMAP LEVEL 0 - BASE
//========================================================
public abstract class LSet0_Base<T> {
	protected Set<T>                           src = new HashSet<>();
	protected List<Set<T>>                     book = new ArrayList<>();
	protected Map<String, Map<Object, Set<T>>> index = new HashMap<>();
	protected Map<Object, Set<T>>              map = new HashMap<>();
	protected Set<T>                           set = new HashSet<>();
	protected Class<T>                         type;
	protected Constructor<T>                   factory;

	{ this.book.add(new HashSet<>()); }
}
//========================================================
//             LSETMAP LEVEL 1 - SUPPORT
//========================================================
@SuppressWarnings("unchecked")
public abstract class LSet1_Support<T> extends LSet0_Base<T> {
	public LSetBook<T> send(Set<T> val, int... pag){
		for (int pos : pag){
			this.book.set(pos, new HashSet<>(val));
		} return (LSetBook<T>) this;
	}

	public <G> void save(T obj, String prop, G val){
		index.putIfAbsent(prop, new HashMap<>());
		index.get(prop).putIfAbsent(val, new HashSet<>());
		index.get(prop).get(val).add(obj);
	}

	public <G> void drop(T obj, String prop, G val){
		if ( ! this.load(prop,val).isEmpty() ){
			this.set.remove(obj);
			if (this.set.isEmpty()){ this.map.remove(val); }
			if (this.map.isEmpty()){ this.index.remove(prop); }
    }
  }

	public <G> Set<T> load(String prop, G val){
		this.map = this.index.containsKey(prop) ? this.index.get(prop) : new HashMap<>();
		this.set = this.map.containsKey(val)  ? this.map.get(val)  : new HashSet<>();
		return this.set;
	}
	public <G> LSetBook<T> pick(int uaib, String prop, G val){
		Set<T> U = new HashSet<>(this.src);
		Set<T> A = new HashSet<>(this.book.get(0));
		Set<T> B = new HashSet<>(this.load(prop,val));
		Set<T> I = new HashSet<>(A);

		I.retainAll(B);
		U.removeAll(A); U.removeAll(B); 
		A.removeAll(I); B.removeAll(I); 

		U = ((uaib & 8) != 0) ? U : new HashSet<>();
		A = ((uaib & 4) != 0) ? A : new HashSet<>();
		I = ((uaib & 2) != 0) ? I : new HashSet<>();
		B = ((uaib & 1) != 0) ? B : new HashSet<>();

		U.addAll(A); U.addAll(B); U.addAll(I);
		this.book.set(0, new HashSet<>(U));
		return (LSetBook<T>) this;
	}
}
//========================================================
//              LSETMAP LEVEL 2 - GENERAL
//========================================================
@SuppressWarnings("unchecked")
public abstract class LSet2_General<T> extends LSet1_Support<T> {
	public LSetBook<T> pag(int... pag){
		book.set(0, this.get(pag));
		return (LSetBook<T>) this;
	}
	public LSetBook<T> New(int n){
		for (int i = 0; i < n; i++) {
			this.book.add(new HashSet<>());
		} return (LSetBook<T>) this;
	}
	public LSetBook<T> rip(int n){
		int size = book.size();
		n = (n < size) ? n : size-1; 
	
		for (int i = 0; i < n; i++) {
			book.remove(size-1-n);
		} return (LSetBook<T>) this;
	}
//========================================================
	public <G> LSetBook<T> set(String prop, G val){
		for (T obj: book.get(0)) { ((LBox<T>) obj).set(prop, val); }
		return (LSetBook<T>) this;
	}
	public T obj(int... pag){
		Iterator<T> pages = this.get(pag).iterator();
		return (pages.hasNext()) ? pages.next() : null;
	}
	public Set<T> get(int... pag){ 
		Set<T> pages = new HashSet<>();
		for (int num: pag){ pages.addAll(book.get(num)); }
		return pages;
 	}
	public LSetBook<T> del(int... pag){
		Set<T> pages = this.get(pag);
		for (int num: pag){ book.get(num).clear(); }
		for (T obj: pages){ ((LBox) obj).rstIndex(); }
		this.src.removeAll(pages);
		book.get(0).removeAll(pages);

		return (LSetBook<T>) this;
	}
	public LSetBook<T> add(Object... args){
		Safe code = new Safe(()->{
			LBox.repo((LSetBook<T>) this);
			T instance = this.factory.newInstance(args);
			this.src.add(instance);
			LBox.repo(null);
		});
		code.except("Instancia de objeto falhou");
		return (LSetBook<T>) this;
  }
}
//========================================================
//               LSETMAP LEVEL 3 - TOOLS
//========================================================
@SuppressWarnings("unchecked")
public abstract class LSet3_Tools<T> extends LSet2_General<T> {
	public int qnt(int... pag){ return this.get(pag).size(); }

	public int sum(String prop){ 
		int sum = 0;
		for (T obj: this.get(0)){ sum += ((int) ((LBox<T>) obj).get(prop)); }
		return sum;
	}
//========================================================
	public LSetBook<T> filter(Predicate<T> lambda){
		Stream<T> result = book.get(0).stream().filter(lambda);
		book.set(0,  result.collect(Collectors.toSet()) );
		return (LSetBook<T>) this;
	}
	public LSetBook<T> forEach(Consumer<T> lambda){
		book.get(0).stream().forEach(lambda);
		return (LSetBook<T>) this;
	}
	public <R> Set<R> map(Function<T,R> lambda){
		Stream<R> result = book.get(0).stream().map(lambda);
		return result.collect(Collectors.toSet());
	}
	public T reduce(T start, BinaryOperator<T> lambda){
		return book.get(0).stream().reduce(start, lambda);
	}
}
//========================================================
//                   LSETMAP FINAL
//========================================================
@SuppressWarnings("unchecked")
public class LSetMap<T> extends LSet3_Tools<T> implements LSetBook<T> {
	public LSetMap(Class<T> type) {
		this.type = type;
		this.factory = (Constructor<T>) type.getConstructors()[0];
	}
//========================================================
	public LSetBook<T> to(int... pag){  return send(this.book.get(0), pag); }
	public LSetBook<T> rst(int... pag){ return send(new HashSet<>(), pag); }
	public LSetBook<T> all(int... pag){ return send(this.src, pag); }
	
	public LSetBook<T> inv(int... pag){
		Set<T> inv = new HashSet<>(src);
		inv.removeAll(book.get(0));
		return send(inv, pag);
	}
//========================================================
	public <G> LSetBook<T> pin(String prop, G val){ return pick(0b0011, prop, val); }
	public <G> LSetBook<T> not(String prop, G val){ return pick(0b1100, prop, val); }
	
	public <G> LSetBook<T> sel(String prop, G val){ return pick(0b0010, prop, val); }
	public <G> LSetBook<T> mix(String prop, G val){ return pick(0b0111, prop, val); }
	public <G> LSetBook<T> alt(String prop, G val){ return pick(0b0101, prop, val); }
	public <G> LSetBook<T> cut(String prop, G val){ return pick(0b0100, prop, val); }
	public <G> LSetBook<T> lim(String prop, G val){ return pick(0b0001, prop, val); }

	public <G> LSetBook<T> sep(String prop, G val){ return pick(0b1101, prop, val); }
	public <G> LSetBook<T> out(String prop, G val){ return pick(0b1000, prop, val); }
	public <G> LSetBook<T> eqv(String prop, G val){ return pick(0b1010, prop, val); }
	public <G> LSetBook<T> esc(String prop, G val){ return pick(0b1011, prop, val); }
	public <G> LSetBook<T> ign(String prop, G val){ return pick(0b1110, prop, val); }
}
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
