<template>
    <!-- slider by w3school -->
    <section class="w-full h-full slideshow-container">
        <div class="mySlides fade">
            <img src="../assets/img/Banners/Hardware.png" style="width:100%" />
        </div>

        <div class="mySlides fade">
            <img src="../assets/img/Banners/Sistemas.png" style="width:100%" />
        </div>

        <div class="mySlides fade">
            <img src="../assets/img/Banners/Video Game.png" style="width:100%" />
        </div>

        <div class="mySlides fade">
            <img src="../assets/img/Banners/Celulares.png" style="width:100%" />
        </div>

        <a class="prev" @click="plusSlides(-1)">&#10094;</a>
        <a class="next" @click="plusSlides(1)">&#10095;</a>
    </section>
</template>
  
<script>
export default {
    mounted() {
        this.slideIndex = 1;
        this.showSlides(this.slideIndex);
    },
    data() {
        return {
            slideIndex: 1,
        };
    },
    methods: {
        plusSlides(n) {
            this.showSlides((this.slideIndex += n));
        },
        currentSlide(n) {
            this.showSlides((this.slideIndex = n));
        },
        showSlides(n) {
            const slides = document.getElementsByClassName("mySlides");
            if (n > slides.length) {
                this.slideIndex = 1;
            }
            if (n < 1) {
                this.slideIndex = slides.length;
            }
            for (let i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            slides[this.slideIndex - 1].style.display = "block";
        },
    },
};
</script>
  
<style scoped>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

/* Slideshow container */
.slideshow-container {
    width: 100%;
    position: relative;
    margin: auto;
}

/* Hide the images by default */
.mySlides {
    display: none;
}

/* Next & previous buttons */
.prev,
.next {
    cursor: pointer;
    position: absolute;
    top: 50%;
    width: auto;
    margin-top: -22px;
    padding: 16px;
    color: rgb(255, 255, 255);
    font-size: 18px;
    text-decoration: none;
}

.next {
    right: 0;
    border-radius: 3px 0 0 3px;
}
</style>
  