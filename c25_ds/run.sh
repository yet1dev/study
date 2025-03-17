clear

test(){ ts-node lsetchain.ts; }

execute(){
	deno repl --allow-read \
		--eval 'import { LSetMap, Ride } from "./lsetchain.ts"'
}

compile(){
	tsc lsetchain.ts --target ES2017 --lib ES2017,DOM --outDir lib;
}

case $1 in
	-t)  test;;
	-c)  compile;;
	*)   execute;;
esac
