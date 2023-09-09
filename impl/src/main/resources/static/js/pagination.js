import makeObservable from "./observer.js";

// Тестовый URL - заменить на свой
const URL = 'https://dummyjson.com/products'

const productsList = document.querySelector('.productsList');
const spinner = document.querySelector('.spinner');
const previousBtn = document.querySelector('.previous-btn');
const nextBtn = document.querySelector('.next-btn');

let state = {
  currentPage: 0,
  totalPages: 0,
  limit: 10,
  isLoading: false,
  get isFirstPage() {
    return this.currentPage === 0
  },
  get isLastPage() {
    return this.currentPage === this.totalPages - 1;
  },
};

state = makeObservable(state);
state.observe(async (key, value) => {
  if (key === "currentPage") {
    setHeightToSpinner();
    clearProductsList();
    setActivePagination();
    setNavigationButtons();
    await renderProductsList();
  }

  if (key === "isLoading") {
    if (value) spinner.classList.remove('hidden');
    else spinner.classList.add('hidden');

    setNavigationButtons();
  }
})

function getProductsListHeight() {
  return productsList.getBoundingClientRect().height;
}

function setHeightToSpinner() {
  spinner.style.height = `${getProductsListHeight()}px`;
}

async function getProductsInfo() {
  try {
    state.isLoading = true;
    const request = await fetch(`${URL}?skip=${state.currentPage * state.limit}&limit=${state.limit}`);
    return await request.json();
  } catch (e) {
    console.error(e);
  } finally {
    state.isLoading = false;
  }
}

function clearProductsList() {
  const products = document.querySelectorAll('.list-group-item');
  products.forEach(product => product.remove());
}

function goToNextPage() {
  state.currentPage += 1;
}

function goToPreviousPage() {
  state.currentPage -= 1;
}

function setActivePagination() {
  const paginationElements = document.querySelectorAll('.pagination-item');
  paginationElements.forEach((page, index) => {
    if (index !== state.currentPage) {
      page.classList.remove('active');
      page.style.pointerEvents = "unset";
    } else {
      page.classList.add('active');
      page.style.pointerEvents = "none";
    }
  });
}

async function renderProductsList() {
  try {
    const productsInfo = await getProductsInfo();
    state.totalPages = productsInfo.total / state.limit;

    productsInfo.products.forEach(product => {
      const productItem = document.createElement('li');
      productItem.className = "list-group-item";
      productItem.innerText = `${product.title} - ${product.price}$`;
      productsList.append(productItem);
    })
  } catch (e) {
    console.error(e);
  }
}

function renderPagination() {
  for (let i = state.totalPages; i > 0; i--) {
    const paginationItem = document.createElement('li');
    paginationItem.className = "page-item pagination-item";

    const paginationLink = document.createElement('a');
    paginationLink.className = 'page-link';
    paginationLink.innerText = `${i}`;
    paginationLink.setAttribute('href', 'javascript:void(0);');

    paginationItem.append(paginationLink);
    paginationItem.addEventListener('click', () => {
      state.currentPage = i - 1;
    })

    previousBtn.after(paginationItem);
  }
}

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

function setNavigationButtons() {
  setNavigationButtonsStyle();
  setNavigationButtonsEvents();
}

async function renderApp() {
  await renderProductsList();
  renderPagination();
  setActivePagination();
  setNavigationButtons();
}

renderApp();
