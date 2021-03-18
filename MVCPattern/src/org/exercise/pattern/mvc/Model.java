package org.exercise.pattern.mvc;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class Model implements Iterable<Model.Range> {
	
		private final Deque<Model.Range> ranges=new LinkedList<>();
		
		public record Range (int lower, int higher) {
			
			public Range {
				if(lower>higher) throw new IllegalArgumentException(String.format("Lower boundary %d should be greater or equal to higher boundary %d",lower,higher));
			}
			
			public boolean within(final int value) {
				return lower<=value && value<=higher;
			}
			
			public Range splitRange(final int choice,final int seed) {
				if(choice>=seed) {
					return new Range(lower(),choice);
				}else {
					return new Range(choice+1,higher());
				}
			}
			
			//@Override public int hashCode() { return Objects.hash(lower,higher);}
			/*
			@Override public boolean equals(final Object o) {
				return (o instanceof Range r) && (lower==r.lower && higher==r.higher);
			}*/
			
			@Override public String toString() { return String.format("{%d,%d}",lower,higher);}
			
		}
		
		public void add(final Range range) {
			ranges.addFirst(range);
		}
		
		public void splitLastRange(final int choice,final int seed) {
			add(lastRange().splitRange(choice, seed));
		}

		@Override
		public Iterator<Range> iterator() {
			return ranges.iterator();
		}
		
		public int getSize() {
			return ranges.size();
		}
		
		public Range lastRange() {
			return ranges.peekFirst();//container is always non-empty as it contains at least one range
		}

	}
	
