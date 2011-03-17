(ns cph-clj-incanter.core
  (use [incanter core stats charts io datasets]))


;; Basic plots and distributions

(view (function-plot sin 0 (* 2 Math/PI)))
(view (histogram (sample-normal 1000 :mean 12 :sd 3)))
(view (histogram (sample-beta 1000 :alpha 1 :beta 2)))
(view (histogram (sample-binomial 1000 :size 1000)))
(view (box-plot [1 2 3 4 5]))

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

(def latency (dataset ["Service" "ms"]
		      [["OrderService" 6] 
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
		       ["PriceService" 3]]))
(view latency)
(sel latency :cols :ms)

;; Special syntax: with-data / $
(with-data latency
  (println "mean latency: " (mean ($ :ms)))
  (println "std dev.    : " (sd ($ :ms)))
  (view (histogram ($ :ms))))


(with-data (get-dataset :cars)
  (let [lm (linear-model ($ :dist) ($ :speed))]
    (doto (scatter-plot ($ :speed) ($ :dist))
      (add-lines ($ :speed) (:fitted lm))
      view)))
