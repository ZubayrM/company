import makeObservable from "./observer.js"; // функция которая делает объект отслеживаемым - импортировал из соседнего файла

// Тестовый URL - заменить на свой
const URL = 'https://dummyjson.com/products'

// Список элементов из HTML - document.querySelector позволяет получать их (можешь почитать разные способы в интернете)
const productsList = document.querySelector('.productsList');
const spinner = document.querySelector('.spinner');
const previousBtn = document.querySelector('.previous-btn');
const nextBtn = document.querySelector('.next-btn');

// Хранилище данных (сбрасывается при каждом обновлении страницы)
let state = {
  currentPage: 0,
  totalPages: 0,
  limit: 10,
  isLoading: false,
  // геттер обновляет свое значение в зависимости от других свойств объекта
  get isFirstPage() {
    return this.currentPage === 0;
  },
  get isLastPage() {
    return this.currentPage === this.totalPages - 1;
  },
  get startPage() {
    if (this.isFirstPage) return this.currentPage;
    if (this.isLastPage) return this.currentPage - 2;
    return this.currentPage - 1;
  },
  get endPage() {
    if (this.isFirstPage) return this.currentPage + 2;
    if (this.isLastPage) return this.currentPage;
    return this.currentPage + 1;
  }
};

// Делаем наше хранилище отслеживаемым
state = makeObservable(state);

// Тут уже следим
state.observe(async (key, value) => {
  // Проверяем необходимое нам именование поля (key)
  if (key === "currentPage") {
    // И затем если оно меняло свое значение (currentPage - текущая страница) -
    // вызываем другие функции и вообще что угодно можно делать тут после изменения
    setHeightToSpinner();
    clearProductsList();
    await renderProductsList();
  }

  if (key === "isLoading") {
    if (value) {
      showSpinner();
    } else {
      hideSpinner();
    }

    setNavigationButtons();
    setPagination();
  }
})

function hideSpinner() {
  spinner.classList.add('hidden');
}

function showSpinner() {
  spinner.classList.remove('hidden');
}

// Получаем высоту списка - для того чтобы выставить такую же для нашей обертки спиннера (чтобы пагинация не уходило наверх)
function getProductsListHeight() {
  return productsList.getBoundingClientRect().height;
}

// Тут выставляем высоту обертке спиннера
function setHeightToSpinner() {
  spinner.style.height = `${getProductsListHeight()}px`;
}

// Получаем список товаров и другую его инфу через асинхронные запросы (fetch) и обрабатываем его в try / catch / finally
// Для обработки асинхронных запросов нужно обернуть функцию в async
async function getProductsInfo() {
  try {
    // обновляем состояние isLoading для того чтобы по его состоянию показывать спиннер
    state.isLoading = true;
    // делаем запрос и ждем с помощью await
    const request = await fetch(`${URL}?skip=${state.currentPage * state.limit}&limit=${state.limit}`);
    // Возвращаем результат обработки в виде объекта (после обработки await request.json() - станет объектом)
    return await request.json();
  } catch (e) {
    // Отлавливаем ошибки, если запрос упал например, чтобы интерфейс продолжал работать
    console.error(e);
  } finally {
    // обновляем состояние isLoading для того чтобы по его состоянию показывать спиннер
    state.isLoading = false;
  }
}

// Очищаем список товаров
function clearProductsList() {
  const products = document.querySelectorAll('.list-group-item');
  products.forEach(product => product.remove());
}

// Показываем список товаров
async function renderProductsList() {
  try {
    // данные которые приходят из запроса можно посмотреть во вкладке Network (total, skip, products)
    const productsInfo = await getProductsInfo();
    // Настройка общего количества страниц
    state.totalPages = productsInfo.total / state.limit;

    // Берем полученный массив и отрисовываем список
    productsInfo.products.forEach(product => {
      // Создаем элемент li для списка товаров из HTML
      const productItem = document.createElement('li');
      // Классы добавил из Bootstrap - чтобы соттветствовало стилю
      productItem.className = "list-group-item";
      // Через innerText можно задавать текст тега
      productItem.innerText = `${product.title} - ${product.price}$`;

      // Добавляем в список (который пока пустой в HTML) наш элемент
      // И так пока все элементы из массива productsInfo.products не добавятся
      productsList.append(productItem);
    })
  } catch (e) {
    console.error(e);
  }
}

function removePagination() {
  const paginationList = document.querySelectorAll('.pagination-item');
  paginationList.forEach(paginationItem => paginationItem.remove());
}

// Отрисовываем пагинацию
function renderPagination() {
  // Итерируемся по количеству state.totalPages
  for (let i = state.endPage + 1; i > state.startPage; i--) {
    // Создаем элемент li для списка пагинации из HTML
    const paginationItem = document.createElement('li');
    // Классы добавил из Bootstrap - чтобы соттветствовало стилю и свой класс pagination-item, чтобы потом найти их
    paginationItem.className = "page-item pagination-item";

    // Создаем ссылки, чтобы потом их вложить в li - требуется для сохранения стиля из Bootstrap
    // А вообще пишут без них обычно на одностраничных приложениях SPA (React, VUE, Angular)
    const paginationLink = document.createElement('a');
    // Класс добавил из Bootstrap - чтобы соттветствовало стилю
    paginationLink.className = 'page-link';
    // Текст кнопки их пагинации
    paginationLink.innerText = `${i}`;

    // это добавление атрибута href для ссылки - так как они требуются ссылкам
    // но я их отключил - чтобы не перезагружали страницу при нажатии через - javascript:void(0);
    // Опять же оставил их для сохранения стиля Bootstrap
    paginationLink.setAttribute('href', 'javascript:void(0);');

    // Вложил ссылку (a) в li
    paginationItem.append(paginationLink);

    // Добавил слушатель событий, который реагирует на клик и обновляет текущую страницу
    paginationItem.addEventListener('click', () => {
      state.currentPage = i - 1;
    })

    // добавил эти кнопки с номером страницы после кноки "previous"
    previousBtn.after(paginationItem);
  }
}

// Настраиваем активную пагинацию и отключаем кнопку чтобы повторно не нажимали уже активную или другие кнопки во время загрузки
function setActivePagination() {
  const paginationList = document.querySelectorAll('.pagination-item');
  if (state.isLoading) {
    paginationList.forEach(paginationItem => paginationItem.style.pointerEvents = "none");
  } else {
    paginationList.forEach(paginationItem => paginationItem.style.pointerEvents = "unset");
  }

  paginationList.forEach(page => {
    const currentPaginationValue = Number(page.textContent) - 1;
    if (currentPaginationValue === state.currentPage) {
      page.classList.add('active');
      page.style.pointerEvents = "none";
    }
  });
}

function setPagination() {
  removePagination();
  renderPagination();
  setActivePagination();
}

// След страница
function goToNextPage() {
  state.currentPage += 1;
}

// Пред страница
function goToPreviousPage() {
  state.currentPage -= 1;
}

// Обновляем стиль кнопок Previous и Next
function setNavigationButtonsStyle() {
  if (state.isFirstPage) {
    previousBtn.classList.add('disabled');
  } else {
    previousBtn.classList.remove('disabled');
  }

  if (state.isLastPage) {
    nextBtn.classList.add('disabled');
  } else {
    nextBtn.classList.remove('disabled');
  }
}

// Обновляем слушатели событий кнопок Previous и Next
function setNavigationButtonsEvents() {
  if (state.isFirstPage || state.isLoading) {
    previousBtn.removeEventListener('click', goToPreviousPage)
  } else {
    previousBtn.addEventListener('click', goToPreviousPage)
  }

  if (state.isLastPage || state.isLoading) {
    nextBtn.removeEventListener('click', goToNextPage)
  } else {
    nextBtn.addEventListener('click', goToNextPage)
  }
}

// Объединенная функция просто для удобства вызова
function setNavigationButtons() {
  setNavigationButtonsStyle();
  setNavigationButtonsEvents();
}

// Описание отрисовки программы по шагам
async function renderApp() {
  await renderProductsList();
  setPagination();
  setNavigationButtons();
}

// Запуск программы
renderApp();
