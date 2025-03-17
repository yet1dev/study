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
