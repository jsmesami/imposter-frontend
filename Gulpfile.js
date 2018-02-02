let gulp = require('gulp'),
    sass = require('gulp-sass'),
    cleanCSS = require('gulp-clean-css'),
    concat = require('gulp-concat');


let sassDir = 'resources/public/sass',
    sassSources = [`${sassDir}/*.scss`, `${sassDir}/modules/*.scss`],
    sassDestDir = 'resources/public/css';


gulp.task('dev_sass', () => {
    return gulp.src(sassSources)
        .pipe(sass.sync().on('error', sass.logError))
        .pipe(concat('imposter.css'))
        .pipe(gulp.dest(sassDestDir));
});

gulp.task('build_sass', () => {
    return gulp.src(sassSources)
        .pipe(sass.sync().on('error', sass.logError))
        .pipe(cleanCSS())
        .pipe(concat('imposter.css'))
        .pipe(gulp.dest(sassDestDir));
});

gulp.task('watch_sass', () => {
    gulp.watch(sassSources, ['dev_sass']);
});

gulp.task('dev', ['dev_sass', 'watch_sass']);
gulp.task('build', ['build_sass']);
gulp.task('default', ['build']);
