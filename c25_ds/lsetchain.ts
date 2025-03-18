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
