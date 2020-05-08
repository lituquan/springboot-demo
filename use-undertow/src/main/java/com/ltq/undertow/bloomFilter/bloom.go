//----------------------------------------------------------------------------
// @ Copyright (C) free license,without warranty of any kind .
// @ Author: hollson <hollson@live.com>
// @ Date: 2019-12-06
// @ Version: 1.0.0
//------------------------------------------------------------------------------
package main

import (
	"fmt"

	"github.com/willf/bitset"
)

const DEFAULT_SIZE = 2 << 24

var seeds = []uint{7, 11, 13, 31, 37, 61}

type BloomFilter struct {
	Set   *bitset.BitSet
	Funcs [6]SimpleHash
}

func NewBloomFilter() *BloomFilter {
	bf := new(BloomFilter)
	for i := 0; i < len(bf.Funcs); i++ {
		bf.Funcs[i] = SimpleHash{DEFAULT_SIZE, seeds[i]}
	}
	bf.Set = bitset.New(DEFAULT_SIZE)
	return bf
}

func (bf BloomFilter) Add(value string) {
	for _, f := range bf.Funcs {
		bf.Set.Set(f.hash(value))
	}
}

func (bf BloomFilter) Contains(value string) bool {
	if value == "" {
		return false
	}
	ret := true
	for _, f := range bf.Funcs {
		ret = ret && bf.Set.Test(f.hash(value))
	}
	return ret
}

type SimpleHash struct {
	Cap  uint
	Seed uint
}

func (s SimpleHash) hash(value string) uint {
	var result uint = 0
	for i := 0; i < len(value); i++ {
		result = result*s.Seed + uint(value[i])
	}
	return (s.Cap - 1) & result
}
func main() {
	filter := NewBloomFilter()
	fmt.Println(filter.Funcs[1].Seed)
	str1 := "hello,bloom filter!"
	filter.Add(str1)
	str2 := "A happy day"
	filter.Add(str2)
	str3 := "Greate wall"
	filter.Add(str3)

	fmt.Println(filter.Set.Count())
	fmt.Println(filter.Contains(str1))
	fmt.Println(filter.Contains(str2))
	fmt.Println(filter.Contains(str3))
	fmt.Println(filter.Contains("blockchain technology"))
}
