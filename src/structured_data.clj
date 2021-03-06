(ns structured-data)

(defn do-a-thing [x]
  (let [x2 (+ x x)]
    (Math/pow x2 x2)
   ))

(defn spiff [v]
  (let [v1 (get v 0)
        v3 (get v 2)]
    (+ v1 v3)
  ))

(defn cutify [v]
  (conj v "<3")
  )

(defn spiff-destructuring [v]
  (let [[a _ b] v]
    (+ a b)
  ))

(defn point [x y]
  [x y])

(defn rectangle [bottom-left top-right]
  [bottom-left top-right])

(defn width [rectangle]
  (let [[[x1 y1] [x2 y2]] rectangle]
    (- x2 x1)
    ))

(defn height [rectangle]
    (let [[[x1 y1] [x2 y2]] rectangle]
      (- y2 y1)
))

(defn square? [rectangle]
  (== (width rectangle) (height rectangle))
  )

(defn area [rectangle]
  (* (width rectangle) (height rectangle)))

(defn contains-point? [rectangle point]
  (let [[[x1 y1] [x2 y2]] rectangle
        [x y] point]
    (and (<= x1 x x2) (<= y1 y y2))
  ))

(defn contains-rectangle? [outer inner]
  (let [ [p1 p2] inner ]
    (and (contains-point? outer p1) (contains-point? outer p2))
  ))

(defn title-length [book]
  (count (:title book) ))

(defn author-count [book]
  (count (:authors book)))

(defn multiple-authors? [book]
  (< 1 (author-count book)))

(defn add-author [book new-author]
  (let [authors (:authors book)
        ]
    (assoc book :authors (conj authors new-author))
    ))

(defn alive? [author]
   (not (contains? author :death-year)))

(defn element-lengths [collection]
  (map count collection))

(defn second-elements [collection]
    (map (fn [l] (get l 1)) collection))

(defn titles [books]
  (map :title books))

(defn monotonic? [a-seq]
  (or (apply <= a-seq) (apply >= a-seq)))

(defn stars [n]
  (apply str (repeat n "*")))

(defn toggle [a-set elem]
  (if (contains? a-set elem)
    (disj a-set elem)
    (conj a-set elem)
  ))

(defn contains-duplicates? [a-seq]
  (not (== (count a-seq) (count (set a-seq)))))

(defn old-book->new-book [book]
  (assoc book :authors (set (:authors book))))

(defn has-author? [book author]
  (contains?  (:authors book) author))

(defn authors [books]
  (apply clojure.set/union (map :authors books)))

(defn all-author-names [books]
  (set (map :name (authors books))))

(defn author->string [author]
  (let [name (:name author)
        birth (:birth-year author)
        death (:death-year author)
        time-part (if (contains? author :birth-year)
                    (str " (" birth " - " death ")")
                    "")
        ]
    (str name time-part)))


(defn authors->string [authors]
  (apply str ( interpose ", " (map author->string authors))))

(defn book->string [book]
  (let [title (:title book)
        authors-str (authors->string (:authors book))]
    (str title ", written by " authors-str)))

(defn books->string [books]
  (if (empty? books)
    "No books."
    (let [book-count (count books)
          count-str (str book-count " book" (if (> book-count 1) "s" "") ". ")
          details (apply str (interpose ". " (map book->string books)))]
      (str count-str details ".")
  )))

(defn books-by-author [author books]
  (filter (fn [book] (has-author? book author)) books))

(defn author-by-name [name authors]
  (first (filter (fn [author] (= (:name author) name)) authors)))

(defn living-authors [authors]
  (filter alive? authors))

(defn has-a-living-author? [book]
  (not (empty? (living-authors (:authors book)))))

(defn books-by-living-authors [books]
  (filter has-a-living-author? books))

; %________%
