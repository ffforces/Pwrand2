

Pwrand2 : ListPattern {
	var <>weights;
	*new { arg list, weights, repeats=1;
		^super.new(list, repeats).weights_(weights)
	}
	embedInStream {  arg inval;
		var item, wVal;
		var wStr = weights.normalizeSum.asStream;
		repeats.value(inval).do({ arg i;
			wVal = wStr.next(inval);
			if(wVal.isNil) { ^inval };
			item = list.at(wVal.windex);
			inval = item.embedInStream(inval);
		});
		^inval
	}
	storeArgs { ^[ list, weights, repeats ] }
}
