(ns cph-clj-incanter.core
  (use [incanter core stats charts io]))


;; Basic plots

(view (histogram (sample-normal 1000 :mean 12 :sd 3)))
(view (function-plot sin 0 (* 2 Math/PI)))

;;
;; Basic data manipulation
;;

(def m (dataset [:x :y] [[0 0] [1 0] [0 1] [1 1]]))

(nrow m) ;; number of rows
(ncol m) ;; number of columns
(dim m) ;; [rows columns]

(sel m :rows (range 2))
(sel m :cols :x)

(matrix? m)
(to-matrix m)
(trans (to-matrix m))

(def latency (dataset [:service :time]
		      ["OrderService" 6] 
		      ["OrderService" 12]
		      ["OrderService" 4]
		      ["OrderService" 5]
		      ["OrderService" 7]
		      ["OrderService" 44]
		      ["OrderService" 21]
		      ["OrderService" 2]
		      ["OrderService" 5]
		      ["OrderService" 5]
		      ["PriceService" 3]
		      ["PriceService" 4]
		      ["PriceService" 5]
		      ["PriceService" 3]
		      ["PriceService" 4]
		      ["PriceService" 2]
		      ["PriceService" 2]
		      ["PriceService" 3]))
	      
(mean (sel latency :time))
     
