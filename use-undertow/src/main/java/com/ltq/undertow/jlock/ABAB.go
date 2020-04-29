package main

var a = []string{"A", "B", "C", "D", "E"}
var b = []string{"1", "2", "3", "4", "5"}
var ch = make(chan string)

func main() {
	go func() {
		for _, v := range a {
			ch <- v
			println(<-ch)
		}
	}()
	for _, v := range b {
		println(<-ch)
		ch <- v
	}
}
