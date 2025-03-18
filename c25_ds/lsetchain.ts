//=============================================================
//                   LOGICAL SET CHAIN
//=============================================================
export class Ride {       // EXEMPLE CLASS
	private speed: string;
	private color: string;
	private name: string;

	constructor(speed:string, color:string, name:string) {
		this.speed = speed;
		this.color = color;
		this.name = name;
	}
}
//==============================================================
//                    ABSTRACT DATA TYPE
//==============================================================
export interface LSetChain<T> {
	res(): any;
	all(): Set<T>;
	cur(): Set<T>;

	reg(obj:T): LSetChain<T>;
	add(...args: any[]): LSetChain<T>;

	val(prop:string, uaib:number): any[];
	obj(prop:string, val:any, uaib:number): T;
	get(prop:string, val:any, uaib:number): Set<T>;
	qnt(prop:string, val:any, uaib:number): number;
	has(prop:string, val:any, uaib:number): boolean;

	del(prop:string, val:any, uaib:number): LSetChain<T>;
	set(prop:string, val:any, uaib:number): LSetChain<T>;
	sel(prop:string, val:any, uaib:number): LSetChain<T>;

	map(lambda:any): LSetChain<T>;
	filter(lambda:any): LSetChain<T>;
	forEach(lambda:any): LSetChain<T>;
	reduce(start:T, lambda:any): LSetChain<T>;
}
//==============================================================
//                   LSET0 BASIC COMPONENTS
//==============================================================
abstract class LSet0_Base<T> {
	protected factory: any;
	protected list = new Set<T>();
	protected chain = new Set<T>();
	protected result: any;
	protected index = new Map<string, Map<any, Set<T>>>();

	protected handler = {
		set(obj: any, prop: string, val: any, proxy:any) {
			let old = obj[prop];             // store old value
			obj.repo.drop(proxy, prop, old); // del obj of old index[prop,val]
			obj.repo.save(proxy, prop, val); // add obj in new index[prop,val]

			obj[prop] = val; // update property value
			return true;
		},
	};
}
//==============================================================
//                      LSET1 SUPPORT
//==============================================================
abstract class LSet1_Support<T> extends LSet0_Base<T> {
	protected struct() { return this as unknown as LSetChain<T>;}
	
	protected load(prop:string, val:any): Set<T> {
		return this.index.get(prop)?.get(val) ?? new Set<T>();
	}

	protected save(obj:T, prop:string, val:any): LSetChain<T>{
		let lvl1:Map<any, any> = this.index;         // set lvl1
		lvl1.get(prop) ?? lvl1.set(prop, new Map()); // create if undefined

		let lvl2:Map<any, any> = lvl1.get(prop);     // set lvl2
		lvl2.get(val)  ?? lvl2.set(val, new Set());  // create if undefined

		lvl2.get(val).add(obj); // add object
		return this.struct();
	};

	protected drop(obj:T, prop:string, val:any): LSetChain<T>{
		let lvl1:any = this.index.get(prop) ?? new Map(); // Map
		let lvl2:any = lvl1.get(val)        ?? new Set(); // Set

		lvl2.delete(obj); // remove object from index

		(lvl2.size === 0) && lvl1.delete(val);
		(lvl1.size === 0) && this.index.delete(prop);

		return this.struct();
	}
//==============================================================
	protected pick(prop:string, val:any, uaib:number): Set<T> {
		let X = uaib, U=this.list, A=this.chain, B=this.load(prop,val); 
		let R = (X==15) ? U : (X==6) ? A : (X==3) ? B : new Set();      // O(1)

		if (!uaib in [0, 15, 6, 3]) {  // O(n): Not try a simple selection 
			if (1 & uaib>>3) { U.forEach(x => !B.has(x) && !A.has(x) && R.add(x) ); } // add U
			if (1 & uaib>>2) { A.forEach(x => !B.has(x) &&  R.add(x) ); } // add A
			if (1 & uaib>>1) { A.forEach(x =>  B.has(x) &&  R.add(x) ); } // add I
			if (1 & uaib>>0) { B.forEach(x => !A.has(x) &&  R.add(x) ); } // add A
		}
		return R;
	}
}
	}
