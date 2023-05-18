// Находим сами элементы в html коде в котором мы используем этот файл js
const button = document.getElementById('showHideContent');
const div = document.querySelector('.filter');
console.log({button});
console.log({div});

// Добавляем слушатель событий на клик
button.addEventListener('click', () => {
  // Если у дива который мы хотим скрыть уже указан класс hidden - мы удаляем его
  // И элемент показывается на странице
  if (div.classList.contains('hidden')) {
    div.classList.remove('hidden');
  } else {
    // Иначе мы добавляем этот класс и соответственно наш элемент
    // (в нашем случае див, но можно к любому добавить) будет скрыт
    div.classList.add('hidden');
  }
})
