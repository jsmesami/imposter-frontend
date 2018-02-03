let gulp = require('gulp'),
    sass = require('gulp-sass'),
    cleanCSS = require('gulp-clean-css');


let sassDir = 'resources/public/sass',
    sassSources = `${sassDir}/**/*.scss`,
    sassMain = `${sassDir}/imposter.scss`,
    sassDestDir = 'resources/public/css';


gulp.task('dev_sass', () => {
    return gulp.src(sassMain)
        .pipe(sass.sync().on('error', sass.logError))
        .pipe(gulp.dest(sassDestDir));
});

gulp.task('build_sass', () => {
    return gulp.src(sassMain)
        .pipe(sass.sync().on('error', sass.logError))
        .pipe(cleanCSS())
        .pipe(gulp.dest(sassDestDir));
});

gulp.task('watch_sass', () => {
    gulp.watch(sassSources, ['dev_sass']);
});

gulp.task('dev', ['dev_sass', 'watch_sass']);
gulp.task('build', ['build_sass']);
gulp.task('default', ['build']);
